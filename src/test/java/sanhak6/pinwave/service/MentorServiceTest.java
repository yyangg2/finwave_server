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

    // 멘토 포트폴리오 등록 테스트
    @Test
    public void testLoginMentor() {
        // 테스트용 멘토 생성
        Mentor mentor = new Mentor();
        mentor.setEmail("test@example.com");
        mentor.setPassword("testpassword");
        mentorService.join(mentor);

        // 로그인 테스트
        Mentor loggedInMentor = mentorService.loginMentor("test@example.com", "testpassword");
        assertNotNull(loggedInMentor);
        assertEquals("test@example.com", loggedInMentor.getEmail());
    }

    @Test
    public void testUpdateMentorPortfolio() throws MentorService.NotFoundException {
        // 테스트용 멘토 생성
        Mentor mentor = new Mentor();
        mentor.setEmail("test@example.com");
        mentor.setPassword("testpassword");
        mentorService.join(mentor);

        // 포트폴리오 정보 업데이트 테스트
        mentorService.updateMentorPortfolio(mentor.getId(), "New introduction", "New job", "Field1", "Field2", "Field3", "New Region");
        Mentor updatedMentor = mentorService.findOne(mentor.getId());

        assertEquals("New introduction", updatedMentor.getIntroduce());
        assertEquals("New job", updatedMentor.getJob());
        assertEquals("Field1", updatedMentor.getField1());
        assertEquals("Field2", updatedMentor.getField2());
        assertEquals("Field3", updatedMentor.getField3());
        assertEquals("New Region", updatedMentor.getRegion());
    }

}