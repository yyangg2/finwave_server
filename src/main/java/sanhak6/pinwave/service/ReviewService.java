package sanhak6.pinwave.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.domain.review.Review;
import sanhak6.pinwave.repository.MenteeRepository;
import sanhak6.pinwave.repository.MentorRepository;
import sanhak6.pinwave.repository.ReviewRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;

    public void saveReview(Review review) { reviewRepository.save(review); }

    public List<Review> findReviews() {
        return reviewRepository.findAll();
    }

    public Review findOne(Long reviewId) {
        return reviewRepository.findOne(reviewId);
    }


    /**
     * 리뷰
     */
    public Long mentorReview(Long mentorId, Long menteeId, float star, String content) {

        //엔티티 조회
        Mentor mentor = mentorRepository.findOne(mentorId);
        Mentee mentee = menteeRepository.findOne(menteeId);

        //리뷰 생성
        Review review = Review.createReviewMentor(mentor, mentee, star, content);

        //리뷰 저장
        reviewRepository.save(review);

        return review.getId();
    }

    public Long menteeReview(Long mentorId, Long menteeId, float star, String content) {

        //엔티티 조회
        Mentor mentor = mentorRepository.findOne(mentorId);
        Mentee mentee = menteeRepository.findOne(menteeId);

        //리뷰 생성
        Review review = Review.createReviewMentee(mentor, mentee, star, content);

        //리뷰 저장
        reviewRepository.save(review);

        return review.getId();
    }
}
