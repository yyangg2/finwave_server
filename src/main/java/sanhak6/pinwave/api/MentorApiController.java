package sanhak6.pinwave.api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanhak6.pinwave.domain.Gender;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.repository.MenteeRepository;
import sanhak6.pinwave.repository.MentorRepository;
import sanhak6.pinwave.service.MenteeService;
import sanhak6.pinwave.service.MentorService;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;


@RestController
@RequiredArgsConstructor
public class MentorApiController {

    private final MentorRepository mentorRepository;
    private final MentorService mentorService;
    private final MenteeRepository menteeRepository;
    private final MenteeService menteeService;

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


// responseDto에 private Long MentorId랑 private Long MenteeId 지정해놓은다음 로직에 따라 적용해줌
// return new LoginResponse(token, findMentor.getId(), "mentor"); //(token, userId, userType)
// return new LoginResponse(token, findMentee.getId(), "mentee"); //(token, userId, userType)


//        if (mentor != null) {
//            // 로그인이 성공하면 토큰을 생성하고 클라이언트에게 반환
//            String token = generateToken(mentor.getEmail());
//            return ResponseEntity.ok(new LoginResponse(token));
//        } else {
//            // 로그인이 실패한 경우 UNAUTHORIZED 상태를 반환
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }

//    @Data
//    static class CreateTodoResponse {
//        private Long id;
//
//        private Long checklistId;
//
//        public CreateTodoResponse(Long id, Long checklistId) {
//            this.id = id;
//            this.checklistId = checklistId;
//        }
//
//    }

    // 토큰 생성
    private String generateToken(String email) {
        String secretKey = "NHvjstQWFI8gF85C7UJiSDfLbqWh1FItQr0S1pR2Ywg=";

        // 토큰 만료 시간 설정 (예: 1시간)
        long expirationTime = 3600000; // 1시간을 밀리초로 표현

        String token = Jwts.builder()
                .setSubject(email) // 토큰의 주제 설정 (이메일 등)
                .setIssuedAt(new Date()) // 토큰 발급 일자 설정
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // 토큰 만료 일자 설정
                .signWith(SignatureAlgorithm.HS256, secretKey) // 토큰 서명 설정
                .compact();

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

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }






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
    @GetMapping("/myPage/mentor/{id}")
    public MentorDto myPageMentor(@PathVariable("id") Long id) {
        Mentor findMentor = mentorRepository.findOne(id);

        return new MentorDto(findMentor);
    }

    @Data
    static class MentorDto {
        private String field1;
        private String field2;
        private String field3;
        private String job;
        private Integer career;
        private String name;
        private Integer count;
        private Integer mentorRank;
        private Integer getReviewCount;
        private Integer doReviewCount;
        private String introduce;

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
}
