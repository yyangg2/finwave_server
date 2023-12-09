package sanhak6.pinwave.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.domain.review.Review;
import sanhak6.pinwave.domain.review.ReviewMentee;
import sanhak6.pinwave.domain.review.ReviewMentor;
import sanhak6.pinwave.repository.MenteeRepository;
import sanhak6.pinwave.repository.MentorRepository;
import sanhak6.pinwave.repository.ReviewRepository;
import sanhak6.pinwave.service.MenteeService;
import sanhak6.pinwave.service.MentorService;
import sanhak6.pinwave.service.ReviewService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class MentorReviewApiController {

    private final ReviewService reviewService;
    private final MenteeService menteeService;
    private final MentorService mentorService;
    private final ReviewRepository reviewRepository;

    /**
     * 등록 API - 리뷰 작성
     */
    @PostMapping("/menteeProfile/{menteeId}/review/{mentorId}")
    public CreateMentorReviewResponse saveReviewMentor(@PathVariable("menteeId") Long menteeId, @PathVariable("mentorId") Long mentorId, @RequestBody @Valid CreateMentorReviewRequest request) {
        ReviewMentor reviewMentor = new ReviewMentor();
        Mentor findMentor = mentorService.findOne(mentorId);
        Mentee findMentee = menteeService.findOne(menteeId);


        reviewMentor.setContent(request.getContent());
        reviewMentor.setStar(request.getStar());
        reviewMentor.setCreateDate(request.getCreateDate());
        reviewMentor.setReviewMentor(findMentor);
        reviewMentor.setReviewMentee(findMentee);

        Long id = reviewService.join(reviewMentor);

        return new CreateMentorReviewResponse(id, findMentor.getId(), findMentee.getId());
    }

    /**
     * 조회 API - 마이페이지에서 내가 남긴 리뷰
     */
    @GetMapping("/myPage/mentor/{id}/doReview")
    public List<ReviewMentorDto> doMentorReviews(@PathVariable("id") Long id) {
        Mentor findMentor = mentorService.findOne(id);
        List<Review> reviewMentors = reviewRepository.doMentorReview(findMentor);
        List<ReviewMentorDto> result = reviewMentors.stream()
                .map(r -> new ReviewMentorDto(r))
                .collect(toList());

        return result;
    }

    /**
     * 조회 API - 마이페이지에서 나에게 남긴 리뷰
     */
    @GetMapping("/myPage/mentor/{id}/getReview")
    public List<ReviewMenteeDto> getMentorReviews(@PathVariable("id") Long id) {
        Mentor findMentor = mentorService.findOne(id);
        List<Review> reviewMentees = reviewRepository.getMentorReview(findMentor);
        List<ReviewMenteeDto> result = reviewMentees.stream()
                .map(r -> new ReviewMenteeDto(r))
                .collect(toList());

        return result;
    }

    /**
     * path variable로 mentorId랑 menteeId받는다.
     *
     */



    @Data
    static class ReviewMenteeDto {
        private Long reviewMenteeId;
        private float star;
        private LocalDateTime createDate;
        private String content;
        private String mentee;
        private String mentor;

        public ReviewMenteeDto(Review reviewMentee) {
            reviewMenteeId = reviewMentee.getId();
            star = reviewMentee.getStar();
            createDate = reviewMentee.getCreateDate();
            content = reviewMentee.getContent();
            mentee = reviewMentee.getReviewMentee().getName();
            mentor = reviewMentee.getReviewMentor().getName();
        }
    }

    @Data
    static class ReviewMentorDto {
        private Long reviewMentorId;
        private float star;
        private LocalDateTime createDate;
        private String content;
        private String mentee;
        private String mentor;

        public ReviewMentorDto(Review reviewMentor) {
            reviewMentorId = reviewMentor.getId();
            star = reviewMentor.getStar();
            createDate = reviewMentor.getCreateDate();
            content = reviewMentor.getContent();
            mentee = reviewMentor.getReviewMentee().getName();
            mentor = reviewMentor.getReviewMentor().getName();
        }

    }

    @Data
    static class CreateMentorReviewRequest {
        private String content;
        private float star;
        private LocalDateTime createDate;
    }

    @Data
    static class CreateMentorReviewResponse {
        private Long id;
        private Long menteeId;
        private Long mentorId;

        public CreateMentorReviewResponse(Long id, Long mentorId, Long menteeId) {
            this.id = id;
            this.mentorId = mentorId;
            this.menteeId = menteeId;
        }

    }

}
