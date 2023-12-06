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
        initService.dbInit4();
        initService.dbInit5();
        initService.dbInit6();
        initService.dbInit7();
        initService.dbInit8();
        initService.dbInit9();
        initService.dbInit10();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Mentor mentor = createMentor("kau1@naver.com", "1111", "강대양", "01011112222", Gender.MAN,
                    "주식", "대출", "부동산", "펀드매니저", "20년 이상", 5, 1, 4, 2,
                    "안녕하세요 강대양입니다", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }

        public void dbInit2() {
            Mentor mentor = createMentor("kau2@naver.com", "2222", "노유림", "01022223333", Gender.WOMAN,
                    "대출", "부동산", "주식", "은행원", "1년", 4, 2, 3, 3,
                    "안녕하세요 노유림입니다", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }

        public void dbInit3() {
            Mentor mentor = createMentor("kau3@naver.com", "3333", "조자운", "01033334444", Gender.MAN,
                    "부동산", "주식", "대출", "부동산중개인", "3년-5년", 3, 3, 2, 1,
                    "안녕하세요 조자운입니다", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }

        public void dbInit4() {
            Mentor mentor = createMentor("kau4@naver.com", "4444", "김민지", "01033334444", Gender.WOMAN,
                    "부동산", "주식", "대출", "부동산중개인", "3년-5년", 3, 4, 5, 1,
                    "안녕하세요 김민지입니다", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }

        public void dbInit5() {
            Mentor mentor = createMentor("kau5@naver.com", "5555", "최지영", "01033334444", Gender.MAN,
                    "부동산", "대출", "주식", "부동산중개인", "3년-5년", 1, 5, 2, 1,
                    "안녕하세요 최지영입니다", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }

        public void dbInit6() {
            Mentor mentor = createMentor("kau6@naver.com", "3333", "하형운", "01033334444", Gender.MAN,
                    "주식", "부동산", "대출", "부동산중개인", "3년-5년", 3, 6, 2, 1,
                    "안녕하세요 하형운입니다", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }

        public void dbInit7() {
            Mentor mentor = createMentor("kau7@naver.com", "3333", "박수빈", "01033334444", Gender.MAN,
                    "주식", "대출", "부동산", "부동산중개인", "3년-5년", 3, 7, 2, 1,
                    "안녕하세요 박수빈입니다", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }

        public void dbInit8() {
            Mentor mentor = createMentor("kau8@naver.com", "3333", "김나은", "01033334444", Gender.MAN,
                    "부동산", "주식", "대출", "부동산중개인", "3년-5년", 3, 8, 2, 1,
                    "안녕하세요 김나은입니다", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }

        public void dbInit9() {
            Mentor mentor = createMentor("kau9@naver.com", "3333", "이은영", "01033334444", Gender.MAN,
                    "부동산", "대출", "주식", "부동산중개인", "3년-5년", 3, 9, 2, 1,
                    "안녕하세요 이은영입니다", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }

        public void dbInit10() {
            Mentor mentor = createMentor("kau10@naver.com", "3333", "김영한", "01033334444", Gender.MAN,
                    "대출", "주식", "부동산", "부동산중개인", "3년-5년", 3, 10, 2, 1,
                    "안녕하세요 김영한입니다", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }


        private Mentor createMentor(String email, String password, String name, String phone, Gender gender, String field1, String field2, String field3,
                                    String job, String career, Integer count, Integer mentorRank, Integer getReviewCount, Integer doReviewCount,
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
