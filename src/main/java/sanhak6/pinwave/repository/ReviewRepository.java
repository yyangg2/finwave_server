package sanhak6.pinwave.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.domain.review.Review;
import sanhak6.pinwave.domain.review.ReviewMentee;
import sanhak6.pinwave.domain.review.ReviewMentor;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final EntityManager em;

    public void save(Review review) {
        if (review.getId() == null) {
            em.persist(review);
        } else {
            em.merge(review);
        }
    }

    public Review findOne(Long id) {
        return em.find(Review.class, id);
    }

    public List<Review> findAll() {
        return em.createQuery("select r from Review r", Review.class)
                .getResultList();
    }

    /**
     *
     * @param mentee
     * @return
     */
    //멘토 -> 멘티
    public List<Review> getMenteeReview(Mentee mentee) {
        return em.createQuery("select r from Review r where treat(r as ReviewMentor).reviewMentee = :mentee", Review.class)
                .setParameter("mentee", mentee)
                .getResultList();
    }

    //멘티 -> 멘토
    public List<Review> doMenteeReview(Mentee mentee) {
        return em.createQuery("select r from Review r where treat(r as ReviewMentee).reviewMentee = :mentee", Review.class)
                .setParameter("mentee", mentee)
                .getResultList();
    }

    /**
     *
     * @param mentor
     * @return
     */
    //멘티 -> 멘토
    public List<Review> getMentorReview(Mentor mentor) {
        return em.createQuery("select r from Review r where treat(r as ReviewMentee).reviewMentor = :mentor", Review.class)
                .setParameter("mentor", mentor)
                .getResultList();
    }

    //멘토 -> 멘티
    public List<Review> doMentorReview(Mentor mentor) {
        return em.createQuery("select r from Review r where treat(r as ReviewMentor).reviewMentor = :mentor", Review.class)
                .setParameter("mentor", mentor)
                .getResultList();
    }

}