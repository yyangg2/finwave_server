package sanhak6.pinwave.domain.review;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.Mentor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Review {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private float star;

    private LocalDateTime createDate;

    @Column(columnDefinition = "text")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Mentee reviewMentee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Mentor reviewMentor;

    //==생성 메서드==//
/*    public static ReviewMentor createReviewMentor(Mentor mentor, Mentee mentee) {
        ReviewMentor reviewMentor = new ReviewMentor();
        reviewMentor.setReviewMentor(mentor);

    }

    public static ReviewMentee createReviewMentee(Mentee mentee, Mentor mentor) {
        ReviewMentee reviewMentee = new ReviewMentee();
        reviewMentee.setReviewMentee(mentee);

    }*/

}
