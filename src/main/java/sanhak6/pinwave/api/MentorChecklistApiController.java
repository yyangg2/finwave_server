package sanhak6.pinwave.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sanhak6.pinwave.domain.*;
import sanhak6.pinwave.domain.review.Review;
import sanhak6.pinwave.domain.review.ReviewMentor;
import sanhak6.pinwave.repository.ChecklistRepository;
import sanhak6.pinwave.repository.MentorRepository;
import sanhak6.pinwave.repository.TodoRepository;
import sanhak6.pinwave.service.ChecklistService;
import sanhak6.pinwave.service.MenteeService;
import sanhak6.pinwave.service.MentorService;
import sanhak6.pinwave.service.TodoService;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class MentorChecklistApiController {

    private final MenteeService menteeService;
    private final MentorService mentorService;
    private final ChecklistRepository checklistRepository;
    private final ChecklistService checklistService;
    private final TodoRepository todoRepository;
    private final TodoService todoService;


    /**
     * 등록 - API
     */
    @PostMapping("/menteeProfile/{menteeId}/checklist/{mentorId}")
    public CreateChecklistResponse saveChecklist(@PathVariable("menteeId") Long menteeId, @PathVariable("mentorId") Long mentorId, @RequestBody @Valid CreateChecklistRequest request) {

        Checklist checklist = new Checklist();
        Mentor findMentor = mentorService.findOne(mentorId);
        Mentee findMentee = menteeService.findOne(menteeId);

        checklist.setChecklistMentor(findMentor);
        checklist.setChecklistMentee(findMentee);




        Long id = checklistService.join(checklist);

        return new CreateChecklistResponse(id, findMentor.getId(), findMentee.getId());
    }


    /**
     * 조회 - API
     */
    @GetMapping("/checklist/mentor/{checklistId}")
    public List<ChecklistDto> checklistMentor(@PathVariable("checklistId") Long checklistId) {
        Checklist findChecklist = checklistRepository.findOne(checklistId);
        List<Checklist> checklists = checklistRepository.findAllWithTodo(findChecklist);
        List<ChecklistDto> result = checklists.stream()
                .map(checklist -> new ChecklistDto(checklist))
                .collect(toList());

        return result;
    }

    /**
     * 수정 - API (할 일 및 체크)
     */
    @PutMapping("/checklist/mentor/{id}")
    public UpdateMentorChecklistResponse updateMentorChecklist(@PathVariable("id") Long id, @RequestBody @Valid UpdateMentorChecklistRequest request) {
        checklistService.updateMentorChecklist(id, request.getTodos());
        Checklist findChecklist = checklistService.findOne(id);

//        Mentor findMentor = mentorService.findOne(id);

        return new UpdateMentorChecklistResponse(findChecklist.getId(), findChecklist.getTodos());
    }

    @Data
    static class UpdateMentorChecklistRequest {
        private List<Todo> todos;
//        private String whatTodo;
//        private Check mentorCheck;
    }


    @Data
    @AllArgsConstructor
    static class UpdateMentorChecklistResponse {
        private Long id;
        private List<Todo> todos;
//        private String whatTodo;
//        private Check mentorCheck;
    }

    @Data
    static class ChecklistDto {
        private List<TodoDto> todos;

        public ChecklistDto(Checklist checklist) {
            todos = checklist.getTodos().stream()
                    .map(todo -> new TodoDto(todo))
                    .collect(toList());
        }
    }

    @Data
    static class TodoDto {
        private String whatTodo;
        private Check mentorCheck; //ENUM [YES, NO]
        private Check menteeCheck; //ENUM [YES, NO]
//        private LocalDateTime dueDate;
        private LocalDate dueDate;

        public TodoDto(Todo todo) {
            whatTodo = todo.getWhatTodo();
            mentorCheck = todo.getMentorCheck();
            menteeCheck = todo.getMenteeCheck();
            dueDate = todo.getDueDate();
        }
    }

    @Data
    static class CreateChecklistRequest {
//        private List<Todo> todos;
    }

    @Data
    static class CreateChecklistResponse {
        private Long id;
        private Long menteeId;
        private Long mentorId;

        public CreateChecklistResponse(Long id, Long mentorId, Long menteeId) {
            this.id = id;
            this.mentorId = mentorId;
            this.menteeId = menteeId;
        }

    }
    
}
