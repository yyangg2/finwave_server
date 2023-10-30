package sanhak6.pinwave.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Todo {

    @Id @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Check mentorCheck; //ENUM [YES, NO]

    @Enumerated(EnumType.STRING)
    private Check menteeCheck; //ENUM [YES, NO]

    private String whatTodo;
}
