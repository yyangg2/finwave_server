package sanhak6.pinwave.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sanhak6.pinwave.domain.*;
import sanhak6.pinwave.repository.ChecklistRepository;
import sanhak6.pinwave.repository.TodoRepository;
import sanhak6.pinwave.service.ChecklistService;
import sanhak6.pinwave.service.MenteeService;
import sanhak6.pinwave.service.MentorService;
import sanhak6.pinwave.service.TodoService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoApiController {

    private final ChecklistRepository checklistRepository;
    private final ChecklistService checklistService;
    private final MenteeService menteeService;
    private final MentorService mentorService;
    private final TodoRepository todoRepository;
    private final TodoService todoService;

    /**
     * 등록 API
     */
    @PostMapping("/todo/{checklistId}")
    public CreateTodoResponse saveChecklist(@PathVariable("checklistId") Long checklistId, @RequestBody @Valid CreateTodoRequest request) {

        Todo todo = new Todo();
        Checklist findChecklist = checklistService.findOne(checklistId);

        todo.setWhatTodo(request.getWhatTodo());
        todo.setMentorCheck(request.getMentorCheck());
        todo.setMenteeCheck(request.getMenteeCheck());
        todo.setDueDate(request.getDueDate());
        todo.setChecklist(findChecklist);

        Long id = todoService.join(todo);

        return new CreateTodoResponse(id, findChecklist.getId());
    }

    @Data
    static class CreateTodoRequest {
        private String whatTodo;
//        private LocalDateTime dueDate;
        private LocalDate dueDate;
        private Check mentorCheck;
        private Check menteeCheck;
    }

    @Data
    static class CreateTodoResponse {
        private Long id;

        private Long checklistId;

        public CreateTodoResponse(Long id, Long checklistId) {
            this.id = id;
            this.checklistId = checklistId;
        }

    }

    /**
     * 수정 - API (멘토)
     */
    @PutMapping("/mentor/todo/{todoId}")
    public UpdateMentorTodoResponse updateMentorTodo(@PathVariable("todoId") Long todoId, @RequestBody @Valid UpdateMentorTodoRequest request) {
        todoService.updateTodoMentor(todoId, request.getWhatTodo(), request.getMentorCheck(), request.getMenteeCheck(), request.getDueDate());
        Todo findTodo = todoService.findTodo(todoId);

        return new UpdateMentorTodoResponse(findTodo.getId(), findTodo.getWhatTodo(), findTodo.getMentorCheck(), findTodo.getMenteeCheck(), findTodo.getDueDate());
    }

    /**
     * 수정 - API (멘티)
     */
    @PutMapping("/mentee/todo/{todoId}")
    public UpdateMenteeTodoResponse updateMenteeTodo(@PathVariable("todoId") Long todoId, @RequestBody @Valid UpdateMenteeTodoRequest request) {
        todoService.updateTodoMentee(todoId, request.getMenteeCheck());
        Todo findTodo = todoService.findTodo(todoId);

        return new UpdateMenteeTodoResponse(findTodo.getId(), findTodo.getMenteeCheck());
    }

    @Data
    static class UpdateMentorTodoRequest {
        private String whatTodo;
        private Check mentorCheck;
        private Check menteeCheck;
        private LocalDate dueDate;
    }


    @Data
    @AllArgsConstructor
    static class UpdateMentorTodoResponse {
        private Long id;
        private String whatTodo;
        private Check mentorCheck;
        private Check menteeCheck;
        private LocalDate dueDate;
    }

    @Data
    static class UpdateMenteeTodoRequest {
        private Check menteeCheck;
    }


    @Data
    @AllArgsConstructor
    static class UpdateMenteeTodoResponse {
        private Long id;
        private Check menteeCheck;
    }

    /**
     * 조회는 체크리스트에서 하면 됨
     */

}
