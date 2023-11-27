package sanhak6.pinwave.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanhak6.pinwave.domain.Check;
import sanhak6.pinwave.domain.Checklist;
import sanhak6.pinwave.domain.Todo;
import sanhak6.pinwave.repository.TodoRepository;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Long join(Todo todo) {
        todoRepository.save(todo);
        return todo.getId();
    }

    public void updateTodoMentor(Long todoId, String whatTodo, Check mentorCheck, Check menteeCheck, LocalDate dueDate) {
        Todo todo = todoRepository.findOne(todoId);

        todo.setWhatTodo(whatTodo);
        todo.setMentorCheck(mentorCheck);
        todo.setMenteeCheck(menteeCheck);
        todo.setDueDate(dueDate);
    }

    public void updateTodoMentee(Long todoId, Check menteeCheck) {
        Todo todo = todoRepository.findOne(todoId);

        todo.setMenteeCheck(menteeCheck);
    }

    public List<Todo> findTodos() { return todoRepository.findAll(); }

    public Todo findTodo(Long todoId) { return todoRepository.findOne(todoId); }

}