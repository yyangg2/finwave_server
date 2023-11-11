package sanhak6.pinwave.api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import sanhak6.pinwave.domain.Gender;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.MenteeMentor;
import sanhak6.pinwave.repository.MenteeRepository;
import sanhak6.pinwave.repository.MentorRepository;
import sanhak6.pinwave.service.MenteeService;
import sanhak6.pinwave.service.MentorService;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class MenteeApiController {

    private final MenteeRepository menteeRepository;
    private final MenteeService menteeService;

    private final MentorRepository mentorRepository;

    @PostMapping("/login/mentee")
    public ResponseEntity<MentorApiController.LoginResponse> loginMentee(@RequestBody @Valid MentorApiController.LoginRequest request) {
        try {
            Mentee mentee = menteeService.loginMentee(request.getEmail(), request.getPassword());

            String token = generateToken(mentee.getEmail());
            return ResponseEntity.ok(new MentorApiController.LoginResponse(token, null));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MentorApiController.LoginResponse(null, "이메일 또는 비밀번호가 잘못되었습니다."));
        }
    }

    private String generateToken(String email) {

            String secretKey = "NHvjstQWFI8gF86C7UJiSDfLbqWh1FItQr0S1pR2Ywg=";

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



    /**
     * 등록 API
     */
    @PostMapping("/register/mentee")
    public CreateMenteeResponse saveMentee(@RequestBody @Valid CreateMenteeRequest request) {

        Mentee mentee = new Mentee();
        mentee.setEmail(request.getEmail());
        mentee.setPassword(request.getPassword());
        mentee.setName(request.getName());
        mentee.setPhone(request.getPhone());
        mentee.setGender(request.getGender());
        mentee.setCreateDate(request.getCreateDate());

        Long id = menteeService.join(mentee);
        return new CreateMenteeResponse(id);
    }

    /**
     * 수정 API
     */
    @PutMapping("/profile/mentee/{id}")
    public UpdateMenteeResponse updateMentee(@PathVariable("id") Long id, @RequestBody @Valid UpdateMenteeRequest request) {
        menteeService.updateMentee(id, request.getIntroduce());
        Mentee findMentee = menteeService.findOne(id);
        return new UpdateMenteeResponse(findMentee.getId(), findMentee.getIntroduce());
    }

    /**
     * 조회 API: 마이페이지
     */
    @GetMapping("/myPage/mentee/{id}")
    public MenteeDto myPageMentee(@PathVariable("id") Long id) {
        Mentee findMentee = menteeRepository.findOne(id);

        return new MenteeDto(findMentee);
    }

//    /**
//     * 조회 API: 메인페이지 - 멘티
//     */
//    @GetMapping("/mainPage/mentee/{id}")
//    public MenteeDto mainPageMentee(@PathVariable("id") Long id) {
//        Mentee findMentee = menteeRepository.findOne(id);
//
//        return new MenteeDto(findMentee);
//    }

    /**
     * 조회 API: 메인페이지
     */
    @GetMapping("/mainPage/mentee/{id}")
    public MenteeDto mainPageMentee(@PathVariable("id") Long id) {
        Mentee findMentee = menteeRepository.findWithMentor(id);

        return new MenteeDto(findMentee);
    }



//    @GetMapping("/mainPage/mentee/{id}")
//    public List<MenteeDto> mentees() {
//        List<Mentee> mentees = menteeRepository.findAllMenteeWithMentor();
//        List<MenteeDto> result = mentees.stream()
//                .map(mentee -> new MenteeDto(mentee))
//                .collect(toList());
//
//        return result;
//    }

//    @GetMapping("/mainPage/mentee/{id}")
//    public List<MenteeMentorMenteeDto> mentees() {
//        List<Mentee> mentees = menteeRepository.findAllMenteeWithMentor();
//        List<MenteeMentorMenteeDto> result = mentees.stream()
//                .map(m -> new MenteeMentorMenteeDto(m))
//                .collect(toList());
//
//        return result;
//    }

//    @Data
//    static class MentorDto {
//        private List<MenteeMentorDto> menteeMentors;
//
//        public MentorDto(Mentor mentor) {
//            menteeMentors = mentor.getMenteeMentors().stream()
//                    .map(menteeMentor -> new MenteeMentorDto(menteeMentor))
//                    .collect(toList());
//        }
//    }

//    @Data
//    static class MenteeMentorMenteeDto {
//        private List<MenteeMentorDto> menteeMentors;
//
//        public MenteeMentorMenteeDto(Mentee mentee) {
//            menteeMentors = mentee.getMenteeMentors().stream()
//                    .map(menteeMentor -> new MenteeMentorDto(menteeMentor))
//                    .collect(toList());
//        }
//    }

    @Data
    static class MenteeMentorDto {
        private String mentorName;

        public MenteeMentorDto(MenteeMentor menteeMentor) {
            mentorName = menteeMentor.getMentor().getName();
        }
    }

//    @Data
//    static class MenteeDto2 {
//
//
//    }

    @Data
    static class MenteeDto {
        private String goal;
        private Integer age;
        private String job;
        private String name;
        private Integer count;
        private Integer menteeRank;
        private Integer getReviewCount;
        private Integer doReviewCount;
        private String introduce;
        private LocalDateTime createDate;

        private List<MenteeMentorDto> menteeMentors;

        public MenteeDto(Mentee mentee) {
            goal = mentee.getGoal();
            age = mentee.getAge();
            job = mentee.getJob();
            name = mentee.getName();
            count = mentee.getCount();
            menteeRank = mentee.getMenteeRank();
            getReviewCount = mentee.getGetReviewCount();
            doReviewCount = mentee.getDoReviewCount();
            introduce = mentee.getIntroduce();
            createDate = mentee.getCreateDate();
            menteeMentors = mentee.getMenteeMentors().stream()
                    .map(menteeMentor -> new MenteeMentorDto(menteeMentor))
                    .collect(toList());
        }
    }

    @Data
    static class UpdateMenteeRequest {
        private String introduce;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMenteeResponse {
        private Long id;
        private String introduce;
    }

    @Data
    static class CreateMenteeRequest {
        private String email;
        private String password;
        private String name;
        private String phone;
        private Gender gender;
        private LocalDateTime createDate;
    }

    @Data
    static class CreateMenteeResponse {
        private Long id;

        //생성자
        public CreateMenteeResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class LoginRequest {
        private String email;
        private String password;
    }

    @Data
    static class LoginResponse {
        private String token;
        private String errorMessage;
    }




}

