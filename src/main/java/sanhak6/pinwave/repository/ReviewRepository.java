package sanhak6.pinwave.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.domain.review.Review;

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


}
