package sanhak6.pinwave.domain;

import lombok.Getter;
import lombok.Setter;
import sanhak6.pinwave.domain.review.Review;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mentor {

    @Id
    @GeneratedValue
    @Column(name = "mentor_id")
    private Long id;

    private Integer age;

    private String region;

    private String job;

    private Integer career; //경력

    private String field1;
    private String field2;
    private String field3;

    private Integer mentorRank;

    private Integer count; //멘토링 횟수
    private Integer getReviewCount; //받은 리뷰 개수
    private Integer doReviewCount; //해준 리뷰 개수

    private String name; //실명
    private String email; //이메일
    private String password; //비밀번호
//    private String gender;
    private String phone;
    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    private Gender gender; //ENUM [MAN, WOMAN]

//    @Embedded
//    private User user;

    @Column(columnDefinition = "text")
    private String introduce;

    //cascade 사용하면 안 될듯.. review, checklist, notice, message가 mentor랑 mentee 둘 다로부터 참조당하기 때문
    @OneToMany(mappedBy = "reviewMentor")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "checklistMentor")
    private List<Checklist> checklists = new ArrayList<>();

    @OneToMany(mappedBy = "noticeMentor")
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "messageMentor")
    private List<Message> messages = new ArrayList<>();

//    @OneToMany(mappedBy = "menteeMentorMentor")
//    private List<MenteeMentor> menteeMentors = new ArrayList<>();


    //==연관관계 메서드==// (with Review)
    public void addReview(Review review) {
        reviews.add(review); //Mentor -> Review
        review.setReviewMentor(this); // Review -> Mentor
    }

    //==연관관계 메서드==// (with Checklist)
    public void addChecklist(Checklist checklist) {
        checklists.add(checklist); //Mentor -> Checklist
        checklist.setChecklistMentor(this); //Checklist -> Mentor
    }

    //==연관관계 메서드==// (with Notice)
    public void addNotice(Notice notice) {
        notices.add(notice); //Mentor -> Notice
        notice.setNoticeMentor(this); //Notice -> Mentor
    }

    //==연관관계 메서드==// (with Message)
    public void addMessage(Message message) {
        messages.add(message); //Mentor -> Message
        message.setMessageMentor(this); //Message -> Mentor
    }

//    //==연관관계 메서드==// (with MenteeMentor)
//    public void addMenteeMentor(MenteeMentor menteeMentor) {
//        menteeMentors.add(menteeMentor); //Mentor -> MenteeMentor
//        menteeMentor.setMenteeMentorMentor(this); //MenteeMentor -> Mentor
//    }

    //==비즈니스 로직==//
    /**
     * 리뷰 수 증가
     */

    //받은 리뷰 수 증가
    public void addGetStock() {
        this.getReviewCount += 1;
    }

    //해준 리뷰 수 증가
    public void addDoStock() {
        this.doReviewCount += 1;
    }

}
