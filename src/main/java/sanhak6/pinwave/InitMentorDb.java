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
                    "안녕하세요, 저는 강대양입니다.\n" +
                            "저는 항상 재정적인 문제에 대해 차분하고 분석적인 접근을 해왔습니다.\n" +
                            "지난 15년 동안 저는 투자 은행 업무와 자산 관리 업무를 할 수 있는 특권을 누려 왔습니다.\n" +
                            "저에게 재무 안정성과 계획은 단순히 유행어가 아니라 안전한 재무 미래의 초석이 됩니다.\n" +
                            "저는 특히 사람들이 재무 포트폴리오를 다양화하고 의미 있는 재무 목표를 세울 수 있도록 돕고 싶습니다.\n" +
                            "궁금한 점이 있거나 안내가 필요하시면 제 경험과 통찰력을 공유하여 금융 여정에 도움을 드리고자 합니다.\n", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }

        public void dbInit2() {
            Mentor mentor = createMentor("kau2@naver.com", "2222", "노유림", "01022223333", Gender.WOMAN,
                    "대출", "부동산", "주식", "은행원", "1년", 4, 2, 3, 3,
                    "안녕하세요, 저는 당신의 부동산 금융 및 주택 담보 대출 전문가인 노유림입니다.\n" +
                            "저의 접근 방식은 모두 정확성과 명확성에 관한 것입니다. 왜냐하면 저는 감정적이거나 모호한 이야기를 좋아하는 사람이 아니기 때문입니다.\n" +
                            "제 분야에서 15년 동안 쌓아온 탄탄한 경험을 바탕으로, 저는 제 전문성에 큰 자부심을 느끼며, 부동산 금융 및 주택 담보 대출 분야에서 충분한 정보를 바탕으로 의사 결정을 내리는 데 필요한 가장 정확하고 신뢰할 수 있는 통찰력을 제공하는 데 전념하고 있습니다.\n", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }

        public void dbInit3() {
            Mentor mentor = createMentor("kau3@naver.com", "3333", "조자운", "01033334444", Gender.MAN,
                    "부동산", "주식", "대출", "부동산중개인", "3년-5년", 3, 3, 2, 1,
                    "안녕하세요, 저는 책임감과 데이터 중심의 의사 결정을 선호하는 기업 재무 분석가 조자운입니다.\n" +
                            "저의 핵심 가치는 기업의 재무 건전성을 면밀히 조사하고 미래 성장 가능성을 평가하는 것에 있습니다.\n" +
                            "글로벌 기업 재무 분석가로서 12년 이상의 경험을 쌓으며 기업 재무 분석과 재무 모델링에 대한 깊은 이해를 쌓았습니다.\n" +
                            "저의 주된 관심사는 기업의 재무 건전성을 보장하고 효과적인 투자 전략을 세우는 것입니다.\n" +
                            "저는 제 전문 지식을 공유하고 여러분이 기업 금융의 세계를 개척하는 것을 돕기 위해 여기에 왔습니다.\n", LocalDateTime.now());

            em.persist(mentor);

//            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentor(mentor);
//            em.persist(menteeMentor);
        }

        public void dbInit4() {
            Mentor mentor = createMentor("kau4@naver.com", "4444", "이영배", "01033334444", Gender.WOMAN,
                    "부동산", "주식", "대출", "부동산중개인", "3년-5년", 3, 4, 5, 1,
                    "안녕하세요! 여러분들의 금융 여정에 함께하게 된 멘토로서 매우 기쁩니다.\n" +
                            "나는 금융 분야에서의 전문 지식을 바탕으로 재테크와 투자에 대한 실질적이고 현실적인 조언을 제공하는 것을 즐깁니다. 금융 전략의 핵심과 효율적인 자산 관리에 대한 이해를 통해 여러분의 재무적인 목표를 달성하는 데 도움이 되고 싶어졌어요.\n" +
                            "내 성격은 적극적이고 실용적인 편입니다. 무엇보다도, 여러분의 목표 달성을 위해 힘쓰는 모습을 지켜보는 것이 가장 큰 보람입니다. 금융의 복잡성을 단순하게 전달하고, 실제로 적용 가능한 솔루션을 제시하는 것을 목표로 하고 있습니다.\n" +
                            "가치관 측면에서는 돈을 통한 가치 창출을 중요하게 여기며, 금융 지식을 활용하여 개인과 사회적인 성장에 기여하고자 합니다. 함께 일하면서 여러분의 금융적인 목표를 함께 성취하고, 돈이 주는 자유로움을 함께 누려 나가길 기대합니다. 함께 여행하는 동안 서로에게 영감을 주며, 긍정적이고 풍요로운 여정을 만들어 나가요!\n" +
                            "\n", LocalDateTime.now());

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
