package sanhak6.pinwave.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenteeMentor {

    @Id @GeneratedValue
    @Column(name = "mentee_mentor_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    //==생성 메서드==//
//    public static MenteeMentor createMenteeMentorMentor(Mentor mentor) {
//        MenteeMentor menteeMentor = new MenteeMentor();
//        menteeMentor.setMenteeMentorMentor(mentor);
//
//        return menteeMentor;
//    }
//
//    public static MenteeMentor createMenteeMentorMentee(Mentee mentee) {
//        MenteeMentor menteeMentor = new MenteeMentor();
//        menteeMentor.setMenteeMentorMentee(mentee);
//
//        return menteeMentor;
//    }

    //==생성 메서드==//
    public static MenteeMentor createMenteeMentor(Mentor mentor) {
        MenteeMentor menteeMentor = new MenteeMentor();
        menteeMentor.setMentor(mentor);

        return menteeMentor;
    }

}
