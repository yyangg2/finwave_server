package sanhak6.pinwave.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Notice {

    @Id @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private NoticeType noticeType; //ENUM [MESSAGE, MENTORING]

    private LocalDateTime sendDate;

    private String content;//ex) "멘토링 요청이 왔습니다.", "메시지가 도착했습니다." 이런걸 의도한건지?

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Mentor noticeMentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Mentee noticeMentee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;

    //==생성 메서드==//
    public static Notice createNoticeMentoring(Mentee mentee, Mentor mentor) {
        Notice notice = new Notice();
        notice.setNoticeMentee(mentee);
        notice.setNoticeMentor(mentor);
        notice.setNoticeType(NoticeType.MENTORING);

        return notice;
    }

    //==생성 메서드==//
    public static Notice createNoticeMessage(Message message) {
        Notice notice = new Notice();
        notice.setMessage(message);
        notice.setNoticeType(NoticeType.MESSAGE);

        return notice;
    }
}
