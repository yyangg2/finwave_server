package sanhak6.pinwave.service;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.repository.MentorRepository;

import javax.persistence.EntityManager;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MentorServiceTest {

    @Autowired MentorService mentorService;

    @Autowired
    MentorRepository mentorRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Mentor mentor = new Mentor();
        mentor.setEmail("kim");

        //when
        Long savedId = mentorService.join(mentor);

        //then
        assertEquals(mentor, mentorRepository.findOne(savedId));
    }

    @Test
    void 중복_회원_예외() throws Exception
    {
        //given
        String name = "kim";

        Mentor memberA = new Mentor();
        memberA.setEmail(name);

        Mentor memberB = new Mentor();
        memberB.setEmail(name);

        //when
        mentorService.join(memberA);

        //then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> mentorService.join(memberB));
        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
    }

    @Test
    void 회원_로그인() {
        // Given
        String email = "user@example.com";
        String password = "password123";

        Mentor mentor = new Mentor();
        mentor.setEmail(email);
        mentor.setPassword(password);
        mentorService.join(mentor);

        // When
        Mentor token = mentorService.loginMentor(email, password);

        // Then
        assertNotNull(token);
    }

    @Test
    void 회원_로그인_유효하지_않은_비밀번호() {
        // Given
        String email = "user@example.com";
        String correctPassword = "password123";
        String incorrectPassword = "wrongPassword";

        Mentor mentor = new Mentor();
        mentor.setEmail(email);
        mentor.setPassword(correctPassword);
        mentorService.join(mentor);

        // When
        // 로그인에 유효하지 않은 비밀번호를 사용
        Mentor token = mentorService.loginMentor(email, incorrectPassword);

        // Then
        assertNull(token);
    }

    @Test
    void 회원_로그인_유효하지_않은_이메일() {
        // Given
        String correctEmail = "user@example.com";
        String incorrectEmail = "nonexistent@example.com";
        String password = "password123";

        Mentor mentor = new Mentor();
        mentor.setEmail(correctEmail);
        mentor.setPassword(password);
        mentorService.join(mentor);

        // When
        // 유효하지 않은 이메일로 로그인 시도
        Mentor token = mentorService.loginMentor(incorrectEmail, password);

        // Then
        assertNull(token);
    }

}