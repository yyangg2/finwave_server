package sanhak6.pinwave.domain;

import lombok.Getter;
import lombok.Setter;
import sanhak6.pinwave.domain.review.Review;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Mentee {

    @Id
    @GeneratedValue
    @Column(name = "mentee_id")
    private Long id;

    private Integer age;

    private String goal;

    private String job;

    private String region;

    private Integer rank;

    private Integer count; //멘토링 횟수

    @Enumerated(EnumType.STRING)
    private Level KnowLevel; //ENUM [HIGH, MEDIUM, LOW]

    @Enumerated(EnumType.STRING)
    private Level AssetLevel; //ENUM [HIGH, MEDIUM, LOW]

    @Embedded
    private User user;

    @Column(columnDefinition = "text")
    private String introduce;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "review_id", unique = true)
//    private Review review; //FK

    //cascade 사용하면 안 될듯.. review, checklist, notice, message가 mentor랑 mentee 둘 다로부터 참조당하기 때문
    @OneToMany(mappedBy = "reviewMentee", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "checklistMentee", cascade = CascadeType.ALL)
    private List<Checklist> checklists = new ArrayList<>();

    @OneToMany(mappedBy = "noticeMentee", cascade = CascadeType.ALL)
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "messageMentee", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();


    //==연관관계 메서드==// (with Review)
    public void addReview(Review review) {
        reviews.add(review);
        review.setReviewMentee(this);
    }

    //==연관관계 메서드==// (with Checklist)
    public void addChecklist(Checklist checklist) {
        checklists.add(checklist);
        checklist.setChecklistMentee(this);
    }

    //==연관관계 메서드==// (with Notice)
    public void addNotice(Notice notice) {
        notices.add(notice);
        notice.setNoticeMentee(this);
    }

    //==연관관계 메서드==// (with Message)
    public void addMessage(Message message) {
        messages.add(message);
        message.setMessageMentee(this);
    }
}
