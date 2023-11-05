package sanhak6.pinwave.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.review.ReviewMentee;
import sanhak6.pinwave.domain.review.ReviewMentor;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenteeRepository {

    private final EntityManager em;

    public void save(Mentee mentee) { em.persist(mentee); }

    public Mentee findOne(Long id) { return em.find(Mentee.class, id); }

    public List<Mentee> findAll() {
        return em.createQuery("select m from Mentee m", Mentee.class)
                .getResultList();
    }

    //spring data jpa쓰면 더 간단할듯
    public List<Mentee> findByName(String name) {
        return em.createQuery("select m from Mentee m where m.name = :name", Mentee.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Mentee> findByEmail(String email) {
        return em.createQuery("select m from Mentee m where m.email = :email", Mentee.class)
                .setParameter("email", email)
                .getResultList();
    }

    public Mentee findWithMentor(Long id) {
        return em.createQuery(
                "select m from Mentee m" +
                        " join fetch m.menteeMentors mt" +
                        " join fetch mt.mentor mtt" +
                        " where m.id = :id", Mentee.class)
                .setParameter("id", id)
                .getSingleResult();
    }

//    public List<Mentee> findAllMenteeWithMentor() {
//        return em.createQuery(
//                        "select m from Mentee m" +
//                                " join fetch m.menteeMentors mt" +
//                                " join fetch mt.mentor mtt", Mentee.class)
//                .getResultList();
//    }

    //==조회 로직==//
    /**
     * 마이페이지에서 내가 남긴 리뷰 및 나에게 남긴 리뷰 조회
     */
//    //멘티 -> 멘토
//    public List<Review> getReviewFromMentee() {
//        return em.createQuery("select r from Mentee m, Review r where r.dtype = mentee and r.mentee_id = m.mentee_id", Review.class)
//                .getResultList();
//    }
//
//    //멘토 -> 멘티
//    public List<Review> getReviewToMentee() {
//        return em.createQuery("select r from Mentee m, Review r where r.dtype = mentor and r.mentee_id = m.mentee_id", Review.class)
//                .getResultList();
//    }

    //멘토 -> 멘티
    public List<ReviewMentor> getMenteeReviewFromMentorToMentee(Mentee mentee) {
        return em.createQuery("select r from Review r where treat(r as Mentor).reviewMentee.id = mentee.id", ReviewMentor.class)
                .getResultList();
    }

    //멘티 -> 멘토
    public List<ReviewMentee> getMenteeReviewFromMenteeToMentor(Mentee mentee) {
        return em.createQuery("select r from Review r where type(r as Mentee).reviewMentee.id = mentee.id", ReviewMentee.class)
                .getResultList();
    }
}
