package sanhak6.pinwave.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Check mentorCheck; //ENUM [YES, NO]

    @Enumerated(EnumType.STRING)
    private Check menteeCheck; //ENUM [YES, NO]

    private String whatTodo;

    //    private LocalDateTime dueDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checklist_id")
    private Checklist checklist;

}