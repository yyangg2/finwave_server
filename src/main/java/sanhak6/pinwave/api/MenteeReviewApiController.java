package sanhak6.pinwave.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sanhak6.pinwave.domain.Gender;
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

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class MenteeReviewApiController {
    private final ReviewService reviewService;
    private final MenteeService menteeService;
    private final MentorService mentorService;
    private final ReviewRepository reviewRepository;


    /**
     * 등록 API - 멘티가 멘토 프로필 들어가서 리뷰 작성하는거임
     */
    @PostMapping("/mentorProfile/{mentorId}/review/{menteeId}")
    public CreateMenteeReviewResponse saveReviewMentee(@PathVariable("mentorId") Long mentorId, @PathVariable("menteeId") Long menteeId, @RequestBody @Valid CreateMenteeReviewRequest request) {
        ReviewMentee reviewMentee = new ReviewMentee();
        Mentee findMentee = menteeService.findOne(menteeId);
        Mentor findMentor = mentorService.findOne(mentorId);

        reviewMentee.setContent(request.getContent());
        reviewMentee.setStar(request.getStar());
        reviewMentee.setCreateDate(request.getCreateDate());
        reviewMentee.setReviewMentee(findMentee);
        reviewMentee.setReviewMentor(findMentor);

//        Long id = reviewService.join(reviewMentee, findMentee, findMentor);
        Long id = reviewService.join(reviewMentee);
//        return new CreateMenteeReviewResponse(id, menteeId, mentorId);
        return new CreateMenteeReviewResponse(id, findMentee.getId(), findMentor.getId());
    }

    /**
     * 조회 API - 마이페이지에서 내가 남긴 리뷰
     */
    @GetMapping("/myPage/mentee/{id}/doReview")
    public List<ReviewMenteeDto> doMenteeReviews(@PathVariable("id") Long id) {
        Mentee findMentee = menteeService.findOne(id);
        List<Review> reviewMentees = reviewRepository.doMenteeReview(findMentee);
        List<ReviewMenteeDto> result = reviewMentees.stream()
                .map(r -> new ReviewMenteeDto(r))
                .collect(toList());

        return result;
    }

    /**
     * 조회 API - 마이페이지에서 나에게 남긴 리뷰
     */
    @GetMapping("/myPage/mentee/{id}/getReview")
    public List<ReviewMentorDto> getMenteeReviews(@PathVariable("id") Long id) {
        Mentee findMentee = menteeService.findOne(id);
        List<Review> reviewMentors = reviewRepository.getMenteeReview(findMentee);
        List<ReviewMentorDto> result = reviewMentors.stream()
                .map(r -> new ReviewMentorDto(r))
                .collect(toList());

        return result;
    }

    @Data
    static class ReviewMenteeDto {
        private Long reviewMenteeId;
        private float star;
        private LocalDateTime createDate;
        private String content;
//        private Mentee mentee;
//        private Mentor mentor;

        public ReviewMenteeDto(Review reviewMentee) {
            reviewMenteeId = reviewMentee.getId();
            star = reviewMentee.getStar();
            createDate = reviewMentee.getCreateDate();
            content = reviewMentee.getContent();
//            mentee = reviewMentee.getReviewMentee();
//            mentor = reviewMentee.getReviewMentor();
        }
    }

    @Data
    static class ReviewMentorDto {
        private Long reviewMentorId;
        private float star;
        private LocalDateTime createDate;
        private String content;
//        private Mentee mentee;
//        private Mentor mentor;

        public ReviewMentorDto(Review reviewMentor) {
            reviewMentorId = reviewMentor.getId();
            star = reviewMentor.getStar();
            createDate = reviewMentor.getCreateDate();
            content = reviewMentor.getContent();
//            mentor = reviewMentor.getReviewMentor();
//            mentee = reviewMentor.getReviewMentee();
        }

    }

    @Data
    static class CreateMenteeReviewRequest {
        private String content;
        private float star;
        private LocalDateTime createDate;
    }

    @Data
    static class CreateMenteeReviewResponse {
        private Long id;
        private Long menteeId;
        private Long mentorId;

        public CreateMenteeReviewResponse(Long id, Long menteeId, Long mentorId) {
            this.id = id;
            this.menteeId = menteeId;
            this.mentorId = mentorId;
        }

    }

}

