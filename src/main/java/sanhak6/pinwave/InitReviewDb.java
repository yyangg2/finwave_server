//package sanhak6.pinwave;
//
//import lombok.RequiredArgsConstructor;
//import sanhak6.pinwave.domain.Mentee;
//import sanhak6.pinwave.domain.Mentor;
//import sanhak6.pinwave.domain.review.ReviewMentee;
//
//import javax.persistence.EntityManager;
//
//public class InitReviewDb {
//
//    public static void addMenteeReviewData(EntityManager entityManager) {
//        Mentor mentor = entityManager.find(Mentor.class, 1);
//        Mentee mentee = entityManager.find(Mentee.class, 12L); // Assuming 12 is the mentee ID
//
//        ReviewMentee menteeReview = ReviewMentee.createReviewMentee(
//                mentee,
//                mentor,
//                5.0f,  // Star rating
//                "멘토님과 함께한 멘토링 수업은 정말로 유익하고 인상적이었습니다."
//        );
//
//        entityManager.persist(menteeReview);
//
//    }
//}
//
