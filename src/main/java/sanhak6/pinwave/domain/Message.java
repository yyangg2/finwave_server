package sanhak6.pinwave.domain;

import lombok.Getter;
import lombok.Setter;
import sanhak6.pinwave.domain.review.ReviewMentor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "message_id")
    private Long id;

    @Column(columnDefinition = "text")
    private String content;

    private LocalDateTime sendDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Mentor messageMentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Mentee messageMentee;

    // 추가

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    public void setMessageMentor(Mentor mentor) {
        this.messageMentor = mentor;
    }

    public void setSenderMentor(Mentor mentor) {
        this.messageMentor = mentor;
    }

    public void setSenderMentee(Mentee mentee) {
        this.messageMentee = mentee;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }


    // Message 엔터티에 메시지 읽음 상태 관련 필드 추가
    private LocalDateTime readDate;

    public void markAsRead() {
        this.readDate = LocalDateTime.now();
    }

    // 메시지가 보내진 시간을 기록하여 화면에 표시
    public Message() {
        this.sendDate = LocalDateTime.now();
    }

    //==생성 메서드==//
    public static Message createMessage(Mentor mentor, Mentee mentee, ChatRoom chatRoom, String content) {
        Message message = new Message();
        message.setMessageMentor(mentor);
        message.setMessageMentee(mentee);
        message.setChatRoom(chatRoom);

        message.setContent(content);
        message.setSendDate(LocalDateTime.now());

        return message;
    }
}

