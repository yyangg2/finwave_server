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
import sanhak6.pinwave.domain.*;
import sanhak6.pinwave.repository.MenteeRepository;
import sanhak6.pinwave.repository.MentorRepository;
import sanhak6.pinwave.service.MenteeService;
import sanhak6.pinwave.service.MentorService;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class MenteeApiController {

    private final MenteeRepository menteeRepository;
    private final MenteeService menteeService;
    private final MentorRepository mentorRepository;

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
    @GetMapping("/mypage/mentee/{id}")
    public MenteeDto myPageMentee(@PathVariable("id") Long id) {
        Mentee findMentee = menteeRepository.findOne(id);

        return new MenteeDto(findMentee);
    }

    /**
     * 조회 API: 메인페이지 멘티가 멘토가 누군지 볼수있는거
     */
    @GetMapping("/mainpage/mentee/{id}")
    public List<MenteeDto> mainPageMentee(@PathVariable("id") Long id) {
        Mentee findMentee = menteeRepository.findOne(id);
        List<Mentee> mentees = menteeRepository.findAllWithMentor(findMentee);
        List<MenteeDto> result = mentees.stream()
                .map(mentee -> new MenteeDto(mentee))
                .collect(toList());

        return result;
    }

    // 멘티메인페이지 - 멘토 랭킹 상위 10명 멘토정보 출력(오름차순)
    @GetMapping("/mainpage/mentee/{id}/mentor-ranking")
    public List<MentorDto> getMentorRankingForMentee(@PathVariable("id") Long menteeId) {
        Mentee findMentee = menteeRepository.findOne(menteeId);
        List<Mentor> mentors = mentorRepository.findAll();

        // 랭킹이 있는 멘토만 필터링하고 랭킹순으로 상위 10명의 멘토 선택
        List<MentorDto> result = mentors.stream()
                .filter(mentor -> mentor.getMentorRank() != null && mentor.getMentorRank() > 0)
                .sorted(Comparator.comparingInt(Mentor::getMentorRank).reversed())
                .limit(10)
                .map(mentor -> new MentorDto(mentor))
                .collect(Collectors.toList());

        return result;
    }
    // 랭킹관련
    @Data
    public class MentorDto {
        private Long id;
        private String region;
        private String job;
        private String career;
        private String field1;
        private String field2;
        private String field3;
        private Integer mentorRank;
        private Integer count;
        private Integer getReviewCount;
        private Integer doReviewCount;
        private String name;
        private String email;
        private String password;
        private String phone;
        private LocalDateTime createDate;

        @Enumerated(EnumType.STRING)
        private Gender gender;

        public MentorDto(Mentor mentor) {
            this.id = mentor.getId();
            this.region = mentor.getRegion();
            this.job = mentor.getJob();
            this.career = mentor.getCareer();
            this.field1 = mentor.getField1();
            this.field2 = mentor.getField2();
            this.field3 = mentor.getField3();
            this.mentorRank = mentor.getMentorRank();
            this.count = mentor.getCount();
            this.getReviewCount = mentor.getGetReviewCount();
            this.doReviewCount = mentor.getDoReviewCount();
            this.name = mentor.getName();
            this.email = mentor.getEmail();
            this.password = mentor.getPassword();
            this.phone = mentor.getPhone();
            this.createDate = mentor.getCreateDate();
            this.gender = mentor.getGender();
        }
    }






    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @PostMapping("/main/mypage/profile-mentee")
    public ResponseEntity<Result<String>> registerMenteeProfile(@RequestBody @Valid RegisterMenteeProfileRequest request) {
        try {
            menteeService.updateMenteeProfile(
                    request.getMenteeId(),
                    request.getIntroduce(),
                    request.getJob(),
                    request.getGoal(),
                    request.getKnowLevel(),
                    request.getRegion(),
                    request.getAssetLevel()
            );
            return ResponseEntity.ok(new Result<>("멘티 프로필이 성공적으로 등록되었습니다."));
        } catch (MenteeService.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Result<>("멘티를 찾을 수 없습니다."));
        }
    }

    @Data
    public static class RegisterMenteeProfileRequest {
        private Long menteeId;
        private String introduce;
        private String job;
        private String goal;
        private String knowLevel;
        private String region;
        private String assetLevel;
    }

    // 멘티 프로필 열람
    @GetMapping("/main/mypage/profile-mentee/{menteeId}")
    public ResponseEntity<Result<MenteeProfileDto>> getMenteeProfile(@PathVariable Long menteeId) {
        try {
            MenteeProfileDto menteeProfileDto = menteeService.getMenteeProfileById(menteeId);
            return ResponseEntity.ok(new Result<>(menteeProfileDto));
        } catch (MenteeService.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Result<>(null));
        }
    }

    @Data
    public static class MenteeProfileDto {
        private Long menteeId;
        private String introduce;
        private String job;
        private String goal;
        private String knowLevel;
        private String region;
        private String assetLevel;

        public MenteeProfileDto(Mentee mentee) {
            this.menteeId = mentee.getId();
            this.introduce = mentee.getIntroduce();
            this.job = mentee.getJob();
            this.goal = mentee.getGoal();
            this.knowLevel = mentee.getKnowLevel();
            this.region = mentee.getRegion();
            this.assetLevel = mentee.getAssetLevel();
        }
    }
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
    static class MenteeMentorDto {
        private String mentorName ;

        public MenteeMentorDto(MenteeMentor menteeMentor) {
            mentorName = menteeMentor.getMenteeMentorMentor().getName();
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
}