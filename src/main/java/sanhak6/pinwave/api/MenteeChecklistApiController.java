package sanhak6.pinwave.api;

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
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class MenteeChecklistApiController {

    private final MenteeService menteeService;
    private final MentorService mentorService;
    private final ChecklistRepository checklistRepository;
    private final ChecklistService checklistService;

    /**
     * 등록 - API : 이건 안 쓸 듯
     */
    @PostMapping("/mentor-profile/{mentorId}/checklist/{menteeId}")
    public CreateChecklistResponse saveChecklist(@PathVariable("mentorId") Long mentorId, @PathVariable("menteeId") Long menteeId, @RequestBody @Valid CreateChecklistRequest request) {

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
    @GetMapping("/checklist/mentee/{checklistId}")
    public List<ChecklistDto> checklistMentee(@PathVariable("checklistId") Long checklistId) {
        Checklist findChecklist = checklistRepository.findOne(checklistId);
        List<Checklist> checklists = checklistRepository.findAllWithTodo(findChecklist);
        List<ChecklistDto> result = checklists.stream()
                .map(checklist -> new ChecklistDto(checklist))
                .collect(toList());

        return result;
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
        private List<Todo> todos;
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