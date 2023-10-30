package sanhak6.pinwave.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
public class User {

    private String name; //실명
//    private String nickname; //닉네임
    private String email; //이메일
    private String password; //비밀번호
    private String gender;
    private String phone;
    private LocalDateTime createDate;
    
    //기본 생성자
    protected User() {
    }

    public User(String name, String nickname, String email, String password, String gender, String phone, LocalDateTime createDate) {
        this.name = name;
//        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.createDate = createDate;
    }
}
