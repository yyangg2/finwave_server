//package sanhak6.pinwave.repository;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//import sanhak6.pinwave.domain.Checklist;
//import sanhak6.pinwave.domain.Notice;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class NoticeRepository {
//
//    private final EntityManager em;
//
//    public void save(Notice notice) { em.persist(notice); }
//
//    public Notice findOne(Long id) { return em.find(Notice.class, id); }
//
//    public List<Checklist> findAll() {
//        return em.createQuery("select c from Checklist c", Checklist.class)
//                .getResultList();
//    }
//
//    public List<Notice> findAllNotice(Notice notice) {
//        return em.createQuery(
//                        "select distinct n from Notice n" +
//                                " join fetch c.todos d" +
//                                " where c = :checklist", Notice.class)
//                .setParameter("checklist", checklist)
//                .getResultList();
//    }
//}
