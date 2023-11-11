package sanhak6.pinwave;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sanhak6.pinwave.domain.Gender;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.MenteeMentor;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitMenteeDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
        initService.dbInit3();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Mentee mentee = createMentee("kau4@naver.com", "4444", "이혜인", "01044445555", Gender.WOMAN,
                    "주식", 22, "학생", 3, 1, 1, 1, "안녕하세요 이혜인입니다", LocalDateTime.now());
            em.persist(mentee);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentee(mentee);
//            em.persist(menteeMentor);


        }

        public void dbInit2() {
            Mentee mentee = createMentee("kau5@naver.com", "5555", "맹성주", "01055556666", Gender.MAN,
                    "대출", 26, "학생", 4, 2, 2, 4, "안녕하세요 맹성주입니다", LocalDateTime.now());
            em.persist(mentee);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentee(mentee);
//            em.persist(menteeMentor);

        }

        public void dbInit3() {
            Mentee mentee = createMentee("kau6@naver.com", "6666", "이진우", "01066667777", Gender.MAN,
                    "부동산", 27, "학생", 5, 3, 4, 3, "안녕하세요 이진우입니다", LocalDateTime.now());

            em.persist(mentee);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentee(mentee);
//            em.persist(menteeMentor);
        }


        private Mentee createMentee(String email, String password, String name, String phone, Gender gender, String goal, Integer age,
                                    String job, Integer count, Integer menteeRank, Integer getReviewCount, Integer doReviewCount, String introduce,
                                    LocalDateTime createDate) {
            Mentee mentee = new Mentee();
            mentee.setEmail(email);
            mentee.setPassword(password);
            mentee.setName(name);
            mentee.setPhone(phone);
            mentee.setGender(gender);
            mentee.setGoal(goal);
            mentee.setAge(age);
            mentee.setJob(job);
            mentee.setCount(count);
            mentee.setMenteeRank(menteeRank);
            mentee.setGetReviewCount(getReviewCount);
            mentee.setDoReviewCount(doReviewCount);
            mentee.setIntroduce(introduce);
            mentee.setCreateDate(createDate);

            return mentee;
        }

    }
}
