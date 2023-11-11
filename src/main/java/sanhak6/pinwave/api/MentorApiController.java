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
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.domain.review.Review;
import sanhak6.pinwave.repository.MentorRepository;
import sanhak6.pinwave.service.MentorService;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;


@RestController
@RequiredArgsConstructor
public class MentorApiController {

    private final MentorRepository mentorRepository;
    private final MentorService mentorService;

    @PostMapping("/login/mentor")
    public ResponseEntity<LoginResponse> loginMentor(@RequestBody @Valid LoginRequest request) {
        try {
            Mentor mentor = mentorService.loginMentor(request.getEmail(), request.getPassword());

            if (mentor != null) {
                // 로그인이 성공하면 토큰을 생성하고 클라이언트에게 반환
                String token = generateToken(mentor.getEmail());
                return ResponseEntity.ok(new LoginResponse(token, null));
            } else {
                // 로그인이 실패한 경우 UNAUTHORIZED 상태와 에러 메시지를 반환
                throw new BadCredentialsException("이메일 또는 비밀번호가 잘못되었습니다.");
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(null, "이메일 또는 비밀번호가 잘못되었습니다."));
        }
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
        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    static class LoginResponse {
        private String token;
        private String errorMessage;
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

    // 멘토 포트폴리오 등록 구현
    @PostMapping("/main/mypage/portfolio_mentor")
    public ResponseEntity<Result<String>> registerMentorPortfolio(@RequestBody @Valid RegisterPortfolioRequest request) {
        try {
            mentorService.updateMentorPortfolio(
                    request.getMentorId(),
                    request.getIntroduce(),
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
        private String job;
        private String field1;
        private String field2;
        private String field3;
        private String region;
    }



}


