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
}