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


    //==생성 메서드==// (멘토 리뷰)
    public static ReviewMentor createReviewMentor(Mentor mentor, Mentee mentee, float star, String content, LocalDateTime createDate) {
        ReviewMentor reviewMentor = new ReviewMentor();
        reviewMentor.setReviewMentor(mentor);
        reviewMentor.setReviewMentee(mentee);

        reviewMentor.setStar(star);
        reviewMentor.setContent(content);
        reviewMentor.setCreateDate(LocalDateTime.now());

        mentor.addDoStock();
        mentee.addGetStock();

        return reviewMentor;
    }

    //==생성 메서드==// (멘티 리뷰)
    public static ReviewMentee createReviewMentee(Mentee mentee, Mentor mentor, float star, String content, LocalDateTime createDate) {
        ReviewMentee reviewMentee = new ReviewMentee();
        reviewMentee.setReviewMentee(mentee);
        reviewMentee.setReviewMentor(mentor);

        reviewMentee.setStar(star);
        reviewMentee.setContent(content);
        reviewMentee.setCreateDate(LocalDateTime.now());

        mentee.addDoStock();
        mentor.addGetStock();

        return reviewMentee;
    }

}