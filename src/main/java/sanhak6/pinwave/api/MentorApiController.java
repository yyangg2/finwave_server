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
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.service.MentorService;
import javax.validation.Valid;
import java.util.Date;


@RestController
@RequiredArgsConstructor
public class MentorApiController {

    private final MentorService mentorService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginMentor(@RequestBody @Valid LoginRequest request) {
        Mentor mentor = mentorService.loginMentor(request.getEmail(), request.getPassword);

        if (mentor != null) {
            // 로그인이 성공하면 토큰을 생성하고 클라이언트에게 반환
            String token = generateToken(mentor.getEmail());
            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            // 로그인이 실패한 경우 UNAUTHORIZED 상태를 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

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


    /**
     * 등록 API
     */
    @PostMapping("/register/mentor")
    public CreateMentorResponse saveMentor(@RequestBody @Valid CreateMentorRequest request) {

        Mentor mentor = new Mentor();
        mentor.setEmail(request.getEmail());
        mentor.setPassword(request.getPassword());
        mentor.setName(request.getName());
        mentor.setPhone(request.getPhone());
        mentor.setGender(request.getGender());

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
//    @GetMapping("/myPage/mentor/{id}")
//    public GetMentorResponse myPageMentor(@PathVariable("id") Long id, ) {
//
//        Mentor findMentor = mentorService.findOne(id);
//
//
//
//        List<Member> findMembers = memberService.findMembers();
//
//
//        //엔티티 -> DTO 변환
//        List<MemberDto> collect = findMembers.stream()
//                .map(m -> new MemberDto(m.getName()))
//                .collect(Collectors.toList());
//
//        return new Result(collect);
//    }

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
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MentorDto {
        private String name;
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
