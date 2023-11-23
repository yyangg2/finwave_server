package sanhak6.pinwave;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sanhak6.pinwave.domain.Gender;
import sanhak6.pinwave.domain.MenteeMentor;
import sanhak6.pinwave.domain.Mentor;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitMentorDb {

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
            Mentor mentor = createMentor("mentor4@naver.com", "1111", "박종서", "01011112222", Gender.MAN,
                    "주식", "대출", "부동산", "펀드매니저", 20, 5, 1, 4, 2,
                    "안녕하세요 박종서입니다", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }

        public void dbInit2() {
            Mentor mentor = createMentor("mentor5@naver.com", "2222", "길현영", "01022223333", Gender.WOMAN,
                    "대출", "부동산", "주식", "은행원", 10, 4, 2, 3, 3,
                    "안녕하세요 길현영입니다", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }

        public void dbInit3() {
            Mentor mentor = createMentor("mentor6@naver.com", "3333", "김형래", "01033334444", Gender.MAN,
                    "부동산", "주식", "대출", "부동산중개인", 5, 3, 3, 2, 1,
                    "안녕하세요 김형래입니다", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }


        private Mentor createMentor(String email, String password, String name, String phone, Gender gender, String field1, String field2, String field3,
                                    String job, Integer career, Integer count, Integer mentorRank, Integer getReviewCount, Integer doReviewCount,
                                    String introduce, LocalDateTime createDate) {
            Mentor mentor = new Mentor();
            mentor.setEmail(email);
            mentor.setPassword(password);
            mentor.setName(name);
            mentor.setPhone(phone);
            mentor.setGender(gender);
            mentor.setField1(field1);
            mentor.setField2(field2);
            mentor.setField3(field3);
            mentor.setJob(job);
            mentor.setCareer(career);
            mentor.setCount(count);
            mentor.setMentorRank(mentorRank);
            mentor.setGetReviewCount(getReviewCount);
            mentor.setDoReviewCount(doReviewCount);
            mentor.setIntroduce(introduce);
            mentor.setCreateDate(createDate);

            return mentor;
        }

    }

}
