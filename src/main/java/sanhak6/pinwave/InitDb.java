package sanhak6.pinwave;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sanhak6.pinwave.domain.Gender;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.MenteeMentor;
import sanhak6.pinwave.domain.Mentor;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Mentee mentee = createMentee("mentee1@naver.com", "1111", "이혜인", "01011112222", Gender.WOMAN,
                    "주식", 22, "학생", 3, 1, 1, 1, "안녕하세요 이혜인입니다", LocalDateTime.now());
            em.persist(mentee);

            Mentor mentor1 = createMentor("mentor1@naver.com", "1111", "강대양", "01011112222", Gender.MAN,
                    "주식", "대출", "부동산", "펀드매니저", 20, 5, 1, 4, 2,
                    "안녕하세요 강대양입니다", LocalDateTime.now());
            em.persist(mentor1);

            Mentor mentor2 = createMentor("mentor2@naver.com", "2222", "김철기", "01022223333", Gender.WOMAN,
                    "대출", "부동산", "주식", "은행원", 10, 4, 2, 3, 3,
                    "안녕하세요 김철기입니다", LocalDateTime.now());
            em.persist(mentor2);

            Mentor mentor3 = createMentor("mentor3@naver.com", "3333", "황수찬", "01033334444", Gender.MAN,
                    "부동산", "주식", "대출", "부동산중개인", 5, 3, 3, 2, 1,
                    "안녕하세요 황수찬입니다", LocalDateTime.now());
            em.persist(mentor3);

            MenteeMentor menteeMentor1 = MenteeMentor.createMenteeMentor(mentee, mentor1);
            em.persist(menteeMentor1);
            MenteeMentor menteeMentor2 = MenteeMentor.createMenteeMentor(mentee, mentor2);
            em.persist(menteeMentor2);
            MenteeMentor menteeMentor3 = MenteeMentor.createMenteeMentor(mentee, mentor3);
            em.persist(menteeMentor3);
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
