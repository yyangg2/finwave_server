package sanhak6.pinwave.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.domain.review.ReviewMentee;
import sanhak6.pinwave.domain.review.ReviewMentor;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MentorRepository {

    private final EntityManager em;

    public void save(Mentor mentor) { em.persist(mentor); }

    public Mentor findOne(Long id) { return em.find(Mentor.class, id); }

    public List<Mentor> findAll() {
        return em.createQuery("select m from Mentor m", Mentor.class)
                .getResultList();
    }


    public Mentor findByEmailAndPassword(String email, String password) {
        List<Mentor> mentors = em.createQuery("SELECT m FROM Mentor m WHERE m.email = :email AND m.password = :password", Mentor.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();

        return mentors.isEmpty() ? null : mentors.get(0);
    }

//    public Mentor findByEmail(String email) {
//
//    }





    //spring data jpa쓰면 더 간단할듯
    public List<Mentor> findByName(String name) {
        return em.createQuery("select m from Mentor m where m.name = :name", Mentor.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Mentor> findByEmail(String email) {
        return em.createQuery("select m from Mentor m where m.email = :email", Mentor.class)
                .setParameter("email", email)
                .getResultList();
    }

    public List<Mentor> findAllMentorWithMentee() {
        return em.createQuery(
                        "select m from Mentor m" +
                                " join fetch m.menteeMentors mt" +
                                " join fetch mt.mentee mtt", Mentor.class)
                .getResultList();
    }

    //==조회 로직==//
    /**
     * 마이페이지에서 내가 남긴 리뷰 및 나에게 남긴 리뷰 조회
     */

//    public List<Review> getReviewFromMentee() {
//        return em.createQuery("select r from Mentor m, Review r where r.dtype = Mentee and r.reviewMentor = m.id", Review.class)
//                .getResultList();
//    }

    //멘티 -> 멘토
    public List<ReviewMentee> getMentorReviewFromMenteeToMentor(Mentor mentor) {
        return em.createQuery("select r from Review r where treat(r as Mentee).reviewMentor.id = mentor.id", ReviewMentee.class)
                .getResultList();
    }


//    public List<Review> getReviewToMentee() {
//        return em.createQuery("select r from Mentor m, Review r where r.dtype = Mentor and r.reviewMentor = m.id", Review.class)
//                .getResultList();
//    }

    //멘토 -> 멘티
    public List<ReviewMentor> getMentorReviewFromMentorToMentee(Mentor mentor) {
        return em.createQuery("select r from Review r where type(r as Mentor).reviewMentor.id = mentor.id", ReviewMentor.class)
                .getResultList();
    }
}
