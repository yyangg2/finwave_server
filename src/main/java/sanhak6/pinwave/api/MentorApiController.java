package sanhak6.pinwave.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sanhak6.pinwave.domain.Gender;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.repository.MentorRepository;
import sanhak6.pinwave.service.MentorService;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@RestController
@RequiredArgsConstructor
public class MentorApiController {

    private final MentorRepository mentorRepository;
    private final MentorService mentorService;

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
