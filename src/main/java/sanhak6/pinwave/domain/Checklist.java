package sanhak6.pinwave.domain;

import lombok.Getter;
import lombok.Setter;
import sanhak6.pinwave.domain.review.ReviewMentor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Checklist {

    @Id
    @GeneratedValue
    @Column(name = "checklist_id")
    private Long id;

//    private LocalDateTime dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Mentor checklistMentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Mentee checklistMentee;

    @OneToMany(mappedBy = "checklist", cascade = CascadeType.ALL)
    private List<Todo> todos = new ArrayList<>();


    //==생성 메서드==//
    public static Checklist createChecklist(Mentor mentor, Mentee mentee, List<Todo> todos) {
        Checklist checklist = new Checklist();
        checklist.setChecklistMentor(mentor);
        checklist.setChecklistMentee(mentee);
        checklist.setTodos(todos);

        return checklist;
    }

}