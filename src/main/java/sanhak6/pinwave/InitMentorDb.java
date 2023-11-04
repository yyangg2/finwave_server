package sanhak6.pinwave;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sanhak6.pinwave.domain.Gender;
import sanhak6.pinwave.domain.Mentor;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

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
            Mentor mentor = createMentor("kau1@naver.com", "1111", "강대양", "01011112222", Gender.MAN,
                    "주식", "대출", "부동산", "펀드매니저", 20, 5, 1, 4, 2, "안녕하세요 강대양입니다");
            em.persist(mentor);
        }

        public void dbInit2() {
            Mentor mentor = createMentor("kau2@naver.com", "2222", "노유림", "01022223333", Gender.WOMAN,
                    "대출", "부동산", "주식", "은행원", 10, 4, 2, 3, 3, "안녕하세요 노유림입니다");
            em.persist(mentor);
        }

        public void dbInit3() {
            Mentor mentor = createMentor("kau3@naver.com", "3333", "조자운", "01033334444", Gender.MAN,
                    "부동산", "주식", "대출", "부동산중개인", 5, 3, 3, 2, 1, "안녕하세요 조자운입니다");
            em.persist(mentor);
        }


        private Mentor createMentor(String email, String password, String name, String phone, Gender gender, String field1, String field2, String field3,
                                    String job, Integer career, Integer count, Integer mentorRank, Integer getReviewCount, Integer doReviewCount, String introduce) {
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

            return mentor;
        }

    }

}
