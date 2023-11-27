package sanhak6.pinwave.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sanhak6.pinwave.domain.Todo;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {
    private final EntityManager em;

    public void save(Todo todo) {
        if (todo.getId() == null) {
            em.persist(todo);
        } else {
            em.merge(todo);
        }
    }

    public Todo findOne(Long id) {
        return em.find(Todo.class, id);
    }

    public List<Todo> findAll() {
        return em.createQuery("select d from Todo d", Todo.class)
                .getResultList();
    }
}