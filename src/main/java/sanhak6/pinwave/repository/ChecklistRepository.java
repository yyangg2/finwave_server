package sanhak6.pinwave.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sanhak6.pinwave.domain.Checklist;
import sanhak6.pinwave.domain.Mentee;


import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChecklistRepository {

    private final EntityManager em;

    public void save(Checklist checklist) { em.persist(checklist); }

    public Checklist findOne(Long id) { return em.find(Checklist.class, id); }

    public List<Checklist> findAll() {
        return em.createQuery("select c from Checklist c", Checklist.class)
                .getResultList();
    }

    public List<Checklist> findAllWithTodo(Checklist checklist) {
        return em.createQuery(
                "select distinct c from Checklist c" +
                        " join fetch c.todos d" +
                        " where c = :checklist", Checklist.class)
                .setParameter("checklist", checklist)
                .getResultList();
    }
}
