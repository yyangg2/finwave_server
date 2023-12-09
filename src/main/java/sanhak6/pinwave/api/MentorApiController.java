package sanhak6.pinwave.api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import sanhak6.pinwave.domain.Gender;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.MenteeMentor;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.repository.MenteeRepository;
import sanhak6.pinwave.repository.MentorRepository;
import sanhak6.pinwave.service.MenteeService;
import sanhak6.pinwave.service.MentorService;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@RestController
@RequiredArgsConstructor
public class MentorApiController {

    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;
    private final MentorService mentorService;
    private final MenteeService menteeService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        Mentor mentor = mentorService.loginMentor(request.getEmail(), request.getPassword());
        Mentee mentee = menteeService.loginMentee(request.getEmail(), request.getPassword());

        if (mentor != null) {
            // mentor 반환
            String token = generateToken(mentor.getEmail());
            return ResponseEntity.ok(new LoginResponse(token, mentor.getId(), "mentor"));
        }
        else if (mentee != null) {
            //mentee 반환
            String token = generateToken(mentee.getEmail());
            return ResponseEntity.ok(new LoginResponse(token, mentee.getId(), "mentee"));
        }
        else { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); }
    }

    // 토큰 생성
    private String generateToken(String email) {
        String secretKey = "NHvjstQWFI8gF85C7UJiSDfLbqWh1FItQr0S1pR2Ywg=";

        // 토큰 만료 시간 설정 (예: 1시간)
        final long EXPIRATION_TIME = 3600000; // 1시간을 밀리초로 표현

        String token = Jwts.builder()
                .setSubject(email) // 토큰의 주제 설정 (이메일 등)
                .setIssuedAt(new Date()) // 토큰 발급 일자 설정
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 토큰 만료 일자 설정
                .signWith(SignatureAlgorithm.HS256, secretKey) // 토큰 서명 설정
                .compact();

        long expirationTime = System.currentTimeMillis() + EXPIRATION_TIME;
        return token;
    }

    @Data
    static class LoginRequest {
        public String getPassword;
        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    static class LoginResponse {
        private String token;
        private Long userId;
        private String userType;
    }
//
    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    // 멘토 회원가입
    @PostMapping("/register/mentor")
    public CreateMentorResponse saveMentor(@RequestBody @Valid CreateMentorRequest request) {

        Mentor mentor = new Mentor();
        mentor.setEmail(request.getEmail());
        mentor.setPassword(request.getPassword());
        mentor.setName(request.getName());
        mentor.setPhone(request.getPhone());
        mentor.setGender(request.getGender());
        mentor.setCreateDate(request.getCreateDate());

        Long id = mentorService.join(mentor);
        return new CreateMentorResponse(id);
    }

    /**
     * 수정 API
     */
    @PutMapping("/profile/mentor/{id}")
    public UpdateMentorResponse updateMentor(@PathVariable("id") Long id, @RequestBody @Valid UpdateMentorRequest request) {
        mentorService.updateMentor(id, request.getIntroduce());
        Mentor findMentor = mentorService.findOne(id);
        return new UpdateMentorResponse(findMentor.getId(), findMentor.getIntroduce());
    }

    /**
     * 조회 API: 마이페이지 - 멘토
     */
    @GetMapping("/mypage/mentor/{id}")
    public MentorDto myPageMentor(@PathVariable("id") Long id) {
        Mentor findMentor = mentorRepository.findOne(id);

        return new MentorDto(findMentor);
    }

    /**
     * 조회 API: 메인페이지
     */
    @GetMapping("/mainpage/mentor/{id}")
    public List<MentorDto> mainPageMentor(@PathVariable("id") Long id) {
        Mentor findMentor = mentorRepository.findOne(id);
        List<Mentor> mentors = mentorRepository.findAllWithMentee(findMentor);
        List<MentorDto> result = mentors.stream()
                .map(mentor -> new MentorDto(mentor))
                .collect(toList());

        return result;
    }

    @Data
    static class MentorDto {
        private String field1;
        private String field2;
        private String field3;
        private String job;
        private String career;
        private String name;
        private Integer count;
        private Integer mentorRank;
        private Integer getReviewCount;
        private Integer doReviewCount;
        private String introduce;

        private LocalDateTime createDate;
        private List<MenteeMentorDto> menteeMentors;

        public MentorDto(Mentor mentor) {
            field1 = mentor.getField1();
            field2 = mentor.getField2();
            field3 = mentor.getField3();
            job = mentor.getJob();
            career = mentor.getCareer();
            name = mentor.getName();
            count = mentor.getCount();
            mentorRank = mentor.getMentorRank();
            getReviewCount = mentor.getGetReviewCount();
            doReviewCount = mentor.getDoReviewCount();
            introduce = mentor.getIntroduce();
            createDate = mentor.getCreateDate();
            menteeMentors = mentor.getMenteeMentors().stream()
                    .map(menteeMentor -> new MenteeMentorDto(menteeMentor))
                    .collect(toList());
        }
    }

    @Data
    static class MenteeMentorDto {
        private Long menteeId;
        private String menteeName ;

        public MenteeMentorDto(MenteeMentor menteeMentor) {
            menteeId = menteeMentor.getMenteeMentorMentee().getId();
            menteeName = menteeMentor.getMenteeMentorMentee().getName();
        }
    }

    @Data
    static class UpdateMentorRequest {
        private String introduce;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMentorResponse {
        private Long id;
        private String introduce;
    }

    @Data
    static class CreateMentorRequest {
        private String email;
        private String password;
        private String name;
        private String phone;
        private Gender gender;
        private LocalDateTime createDate;
    }

    @Data
    static class CreateMentorResponse {
        private Long id;

        //생성자
        public CreateMentorResponse(Long id) {
            this.id = id;
        }
    }

    // 멘토 포트폴리오 등록 구현
    @PostMapping("/main/mypage/portfolio-mentor")
    public ResponseEntity<Result<String>> registerMentorPortfolio(@RequestBody @Valid RegisterPortfolioRequest request) {
        try {
            mentorService.updateMentorPortfolio(
                    request.getMentorId(),
                    request.getIntroduce(),
                    request.getCareer(),
                    request.getJob(),
                    request.getField1(),
                    request.getField2(),
                    request.getField3(),
                    request.getRegion()
            );
            return ResponseEntity.ok(new Result<>("멘토 포트폴리오가 성공적으로 등록되었습니다."));
        } catch (MentorService.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Result<>("멘토를 찾을 수 없습니다."));
        }


    }

    @Data
    static class RegisterPortfolioRequest {
        private Long mentorId;
        private String introduce;
        private String career;
        private String job;
        private String field1;
        private String field2;
        private String field3;
        private String region;
    }

    @GetMapping("main/mypage/portfolio-mentor/{mentorId}")
    public ResponseEntity<Result<MentorPortfolioDto>> getMentorPortfolio(@PathVariable Long mentorId) {
        try {
            MentorPortfolioDto mentorPortfolioDto = mentorService.getMentorPortfolioById(mentorId);
            return ResponseEntity.ok(new Result<>(mentorPortfolioDto));
        } catch (MentorService.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Result<>(new MentorPortfolioDto()));
        }
    }

    @Data
    public static class MentorPortfolioDto {
        private Long mentorId;
        private String name;
        private String introduce;
        private String career;
        private String job;
        private String field1;
        private String field2;
        private String field3;
        private String region;

        public MentorPortfolioDto() {
        }

        public MentorPortfolioDto(Mentor mentor) {
            this.mentorId = mentor.getId();
            this.name = mentor.getName();
            this.introduce = mentor.getIntroduce();
            this.career = mentor.getCareer();
            this.job = mentor.getJob();
            this.field1 = mentor.getField1();
            this.field2 = mentor.getField2();
            this.field3 = mentor.getField3();
            this.region = mentor.getRegion();
        }
    }

    @Data
    public class MentorFilteringDto {
        private String field1;
        private String field2;
        private String field3;
        private String job;
        private String career;
        private String region;

        public MentorFilteringDto(Mentor mentor) {
            this.field1 = mentor.getField1();
            this.field2 = mentor.getField2();
            this.field3 = mentor.getField3();
            this.job = mentor.getJob();
            this.career = mentor.getCareer();
            this.region = mentor.getRegion();
        }
    }

    //필터링
    @GetMapping("/mentor-filtering")
    public ResponseEntity<String> filterMentors(
            @RequestParam(required = false) String career,
            @RequestParam(required = false) String job,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String field1,
            @RequestParam(required = false) String field2,
            @RequestParam(required = false) String field3) {

        List<Mentor> mentors = mentorService.searchMentorsByFilter(career, job, region, field1, field2, field3);
//        List<MentorDto> mentorDtos = mentors.stream()
//                .map(MentorDto::new)
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(mentorDtos);
//    }
        if (!mentors.isEmpty()) {
            return ResponseEntity.ok("멘토 필터링 검색이 성공적으로 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("해당 필터링에 대한 멘토는 존재하지 않습니다.");
        }
    }

    public class FilteredMentorDto {
        private String name;
        private Long id;
        private String career;
//        private String job;
//        private String region;
        private String field1;
        private String field2;
        private String field3;

        public String getName() {
            return name;
        }

        public Long getId() {
            return id;
        }

        public String getCareer() {
            return career;
        }

//        public String getJob() {
//            return job;
//        }
//
//        public String getRegion() {
//            return region;
//        }

        public String getField1() {
            return field1;
        }

        public String getField2() {
            return field2;
        }

        public String getField3() {
            return field3;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setCareer(String career) {
            this.career = career;
        }

//        public void setJob(String job) {
//            this.job = job;
//        }
//
//        public void setRegion(String region) {
//            this.region = region;
//        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        public void setField2(String field2) {
            this.field2 = field2;
        }

        public void setField3(String field3) {
            this.field3 = field3;
        }
    }

    // 멘토 필터링 결과 조회를 위한 API
    @GetMapping("/mentor-filtering/result")
    public ResponseEntity<List<FilteredMentorDto>> filterResults(
            @RequestParam(required = false) String career,
            @RequestParam(required = false) String job,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String field1,
            @RequestParam(required = false) String field2,
            @RequestParam(required = false) String field3) {

        List<Mentor> mentors = mentorService.searchMentorsByFilter(career, job, region, field1, field2, field3);

        if (!mentors.isEmpty()) {
            List<FilteredMentorDto> mentorDtos = mentors.stream()
                    .map(mentor -> {
                        FilteredMentorDto filteredMentorDto = new FilteredMentorDto();
                        filteredMentorDto.setName(mentor.getName());
                        filteredMentorDto.setId(mentor.getId());
                        filteredMentorDto.setCareer(mentor.getCareer());
//                        filteredMentorDto.setJob(mentor.getJob());
//                        filteredMentorDto.setRegion(mentor.getRegion());
                        filteredMentorDto.setField1(mentor.getField1());
                        filteredMentorDto.setField2(mentor.getField2());
                        filteredMentorDto.setField3(mentor.getField3());
                        return filteredMentorDto;
                    })
                    .collect(toList());

            return ResponseEntity.ok(mentorDtos);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        }
    }

}


