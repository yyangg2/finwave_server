package sanhak6.pinwave;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sanhak6.pinwave.domain.Gender;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.MenteeMentor;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.domain.review.ReviewMentee;
import sanhak6.pinwave.domain.review.ReviewMentor;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit0();
        initService.dbInit1();
        initService.dbInit2();
        initService.dbInit3();
        initService.dbInit4();
        initService.dbInit5();
        initService.dbInit6();
//        initService.dbInit7();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit0() {
            Mentee mentee1 = createMentee("kau25@naver.com", "6666", "James", "01066667777", Gender.MAN,
                    "주식", 29, "기타", 5, 3, 4, 3, "안녕하세요, 금융 멘토링 프로그램에 참여하게 된 저는 항상 금융 세계에 대한 탐구와 야망을 품은 열정적인 개인입니다. 금융의 복잡성에 도전하고자 하는 강한 욕망과 함께, 미래를 위해 지속적으로 투자하고 싶어하는 목표를 갖고 있습니다.\n" +
                            "저는 리스크 관리와 금융 안전성을 중시하며, 이를 바탕으로 효율적이고 안정적인 투자 전략을 개발하고자 노력하고 있습니다. 또한, 다양한 금융 상품과 시장 동향에 대한 지식을 확장하여 최신 동향을 반영한 결정을 내릴 수 있도록 노력하고 있습니다.\n" +
                            "멘토와의 소통을 통해 나만의 투자 철학을 더욱 세련되게 발전시키고, 금융적인 안목을 높여 나가고자 합니다. 멘토의 지혜와 지도 아래에서 더 나은 금융적 안정성을 달성하기 위해 열심히 노력할 것을 약속드립니다. 감사합니다.\n", LocalDateTime.now());
            em.persist(mentee1);

            Mentee mentee2 = createMentee("kau26@naver.com", "6666", "Bailey", "01066667777", Gender.MAN,
                    "대출", 50, "기타", 5, 3, 4, 3, "저는 금융에 대한 흥미와 재테크(financial technology), 투자에 대한 강한 관심을 가진 개인으로, 적극적이고 목표 지향적인 성격을 가지고 있습니다. 일상의 금융 의사결정에 있어서는 신중한 성향으로 접근하며, 목표를 달성하기 위해 노력하는 것을 중요하게 생각합니다.\n" +
                            "금융 멘토링 프로그램에 참여하면서 전문가의 지도를 받아 더욱 효과적인 재테크(financial technology) 전략과 투자 방법을 습득하고자 하는 목표를 가지고 있습니다. 다양한 금융 상품과 시장 동향에 대한 이해를 높이며, 개인적인 금융 목표를 달성하기 위해 노력하고 있습니다. 목표 지향적인 성격을 바탕으로 멘토링을 통해 성장의 기회를 극대화하고, 금융적인 안목을 높여 미래의 재무적 안정성을 구축하고자 합니다.\n" +
                            "멘토와의 소통과 지도를 통해 나만의 투자 철학을 더욱 발전시키고, 금융적인 지식을 심화해 나가고자 하는 목표를 가지고 있습니다. 제 경험과 노력을 통해 멘토링 프로그램을 통해 제 금융적인 역량을 향상시키고, 미래를 위한 지혜를 쌓아가는데 적극적으로 참여하겠습니다. 감사합니다.\n", LocalDateTime.now());
            em.persist(mentee2);

            Mentor mentor1 = createMentor("kau3@naver.com", "3333", "Trex", "01033334444", Gender.MAN,
                    "부동산", "주식", "대출", "부동산중개인", "3년-5년", 3, 3, 2, 1,
                    "안녕하세요, 저는 책임감과 데이터 중심의 의사 결정을 선호하는 기업 재무 분석가 Trex입니다." +
                            "저의 핵심 가치는 기업의 재무 건전성을 면밀히 조사하고 미래 성장 가능성을 평가하는 것에 있습니다." +
                            "글로벌 기업 재무 분석가로서 12년 이상의 경험을 쌓으며 기업 재무 분석과 재무 모델링에 대한 깊은 이해를 쌓았습니다." +
                            "저의 주된 관심사는 기업의 재무 건전성을 보장하고 효과적인 투자 전략을 세우는 것입니다." +
                            "저는 제 전문 지식을 공유하고 여러분이 기업 금융의 세계를 개척하는 것을 돕기 위해 여기에 왔습니다.", LocalDateTime.now());
            em.persist(mentor1);

            Mentor mentor2 = createMentor("kau4@naver.com", "4444", "Sulley", "01033334444", Gender.WOMAN,
                    "부동산", "주식", "대출", "부동산중개인", "3년-5년", 3, 4, 5, 1,
                    "안녕하세요! 여러분들의 금융 여정에 함께하게 된 멘토로서 매우 기쁩니다." +
                            "나는 금융 분야에서의 전문 지식을 바탕으로 재테크(financial technology)와 투자에 대한 실질적이고 현실적인 조언을 제공하는 것을 즐깁니다. 금융 전략의 핵심과 효율적인 자산 관리에 대한 이해를 통해 여러분의 재무적인 목표를 달성하는 데 도움이 되고 싶어졌어요." +
                            "내 성격은 적극적이고 실용적인 편입니다. 무엇보다도, 여러분의 목표 달성을 위해 힘쓰는 모습을 지켜보는 것이 가장 큰 보람입니다. 금융의 복잡성을 단순하게 전달하고, 실제로 적용 가능한 솔루션을 제시하는 것을 목표로 하고 있습니다." +
                            "가치관 측면에서는 돈을 통한 가치 창출을 중요하게 여기며, 금융 지식을 활용하여 개인과 사회적인 성장에 기여하고자 합니다. 함께 일하면서 여러분의 금융적인 목표를 함께 성취하고, 돈이 주는 자유로움을 함께 누려 나가길 기대합니다. 함께 여행하는 동안 서로에게 영감을 주며, 긍정적이고 풍요로운 여정을 만들어 나가요!" +
                            "\n", LocalDateTime.now());
            em.persist(mentor2);

            //멘토링 관계 맺기
            MenteeMentor menteeMentor1 = MenteeMentor.createMenteeMentor(mentee1, mentor1);
            em.persist(menteeMentor1);
            MenteeMentor menteeMentor2 = MenteeMentor.createMenteeMentor(mentee1, mentor2);
            em.persist(menteeMentor2);
            MenteeMentor menteeMentor3 = MenteeMentor.createMenteeMentor(mentee2, mentor1);
            em.persist(menteeMentor3);
            MenteeMentor menteeMentor4 = MenteeMentor.createMenteeMentor(mentee2, mentor2);
            em.persist(menteeMentor4);

            //리뷰 작성
            ReviewMentee reviewMentee1 = ReviewMentee.createReviewMentee(mentee1, mentor1, 5, "멘토님과 함께한 멘토링 수업은 정말로 유익하고 인상적이었습니다. 멘토님은 금융 분야에 대한 깊은 지식을 바탕으로 재테크(financial technology)와 투자에 대한 현실적인 조언을 제공해 주셨습니다. 특히, 목표 지향적인 성향을 가진 저에게 맞춤형 조언을 해주셔서 미래의 재무적 안정성을 구축하는 데 큰 도움이 되었습니다. 멘토님의 열정과 실용적인 접근 방식은 매우 감명깊었고, 이제 더 나은 금융적인 결정을 내릴 수 있게 되었습니다.", LocalDateTime.now());
            em.persist(reviewMentee1);
            ReviewMentee reviewMentee2 = ReviewMentee.createReviewMentee(mentee1, mentor2, 5, "멘토님과 함께한 멘토링 수업은 정말로 유익하고 인상적이었습니다. 멘토님은 금융 분야에 대한 깊은 지식을 바탕으로 재테크(financial technology)와 투자에 대한 현실적인 조언을 제공해 주셨습니다. 특히, 목표 지향적인 성향을 가진 저에게 맞춤형 조언을 해주셔서 미래의 재무적 안정성을 구축하는 데 큰 도움이 되었습니다. 멘토님의 열정과 실용적인 접근 방식은 매우 감명깊었고, 이제 더 나은 금융적인 결정을 내릴 수 있게 되었습니다.", LocalDateTime.now());
            em.persist(reviewMentee2);
            ReviewMentee reviewMentee3 = ReviewMentee.createReviewMentee(mentee2, mentor1, 5, "멘토님과의 멘토링 경험은 매우 인상적이었습니다. 금융 분야의 복잡성을 단순하게 전달하고 실제로 적용 가능한 솔루션을 제시하는 멘토님의 명쾌한 설명은 금융에 대한 나의 이해를 크게 향상시켰습니다. 가치 창출을 중요시하는 멘토님의 가치관은 돈을 통한 가치 창출에 대한 새로운 시각을 제공했습니다. 함께한 여정에서 멘토님의 열정과 지혜에 영감을 받아, 미래의 금융적인 목표에 대한 더 큰 자신감을 갖게 되었습니다", LocalDateTime.now());
            em.persist(reviewMentee3);
            ReviewMentee reviewMentee4 = ReviewMentee.createReviewMentee(mentee2, mentor2, 5, "멘토님과의 멘토링 경험은 매우 인상적이었습니다. 금융 분야의 복잡성을 단순하게 전달하고 실제로 적용 가능한 솔루션을 제시하는 멘토님의 명쾌한 설명은 금융에 대한 나의 이해를 크게 향상시켰습니다. 가치 창출을 중요시하는 멘토님의 가치관은 돈을 통한 가치 창출에 대한 새로운 시각을 제공했습니다. 함께한 여정에서 멘토님의 열정과 지혜에 영감을 받아, 미래의 금융적인 목표에 대한 더 큰 자신감을 갖게 되었습니다", LocalDateTime.now());
            em.persist(reviewMentee4);



        }

        public void dbInit1() {
            Mentor mentor = createMentor("kau1@naver.com", "1111", "Ken", "01011112222", Gender.MAN,
                    "주식", "대출", "부동산", "펀드매니저", "20년 이상", 5, 1, 4, 2,
                    "안녕하세요, 저는 Ken입니다." +
                            "저는 항상 재정적인 문제에 대해 차분하고 분석적인 접근을 해왔습니다." +
                            "지난 15년 동안 저는 투자 은행 업무와 자산 관리 업무를 할 수 있는 특권을 누려 왔습니다." +
                            "저에게 재무 안정성과 계획은 단순히 유행어가 아니라 안전한 재무 미래의 초석이 됩니다." +
                            "저는 특히 사람들이 재무 포트폴리오를 다양화하고 의미 있는 재무 목표를 세울 수 있도록 돕고 싶습니다." +
                            "궁금한 점이 있거나 안내가 필요하시면 제 경험과 통찰력을 공유하여 금융 여정에 도움을 드리고자 합니다.", LocalDateTime.now());

            em.persist(mentor);

        }

        public void dbInit2() {
            Mentor mentor = createMentor("kau2@naver.com", "2222", "Holly", "01022223333", Gender.WOMAN,
                    "대출", "부동산", "주식", "은행원", "1년", 4, 2, 3, 3,
                    "안녕하세요, 저는 당신의 부동산 금융 및 주택 담보 대출 전문가인 Holly입니다." +
                            "저의 접근 방식은 모두 정확성과 명확성에 관한 것입니다. 왜냐하면 저는 감정적이거나 모호한 이야기를 좋아하는 사람이 아니기 때문입니다." +
                            "제 분야에서 15년 동안 쌓아온 탄탄한 경험을 바탕으로, 저는 제 전문성에 큰 자부심을 느끼며, 부동산 금융 및 주택 담보 대출 분야에서 충분한 정보를 바탕으로 의사 결정을 내리는 데 필요한 가장 정확하고 신뢰할 수 있는 통찰력을 제공하는 데 전념하고 있습니다.", LocalDateTime.now());

            em.persist(mentor);

        }

        public void dbInit3() {
            Mentee mentee = createMentee("kau20@naver.com", "4444", "Sarah", "01044445555", Gender.WOMAN,
                    "주식", 22, "학생", 3, 1, 1, 1, "안녕하세요, 여러분! 저는 금융의 무한한 가능성을 탐험하는 여정에 도전하고 있는 Sarah입니다.\n" +
                            "제 성격은 호기심과 열정이 가득한데요, 금융 세계에서 새로운 지식을 습득하고 다양한 경험을 통해 성장하는 것에 큰 흥미를 느낍니다.\n" +
                            "교육을 통한 재정적인 안정과 미래 투자에 대한 심사숙고가 제 가치 중 하나입니다. 재무적 기본을 탄탄히 다지고, 저축 습관을 꾸준히 기르며 투자의 세계로 나아가는 여정에서 끊임없는 호기심을 가지고 있습니다.\n" +
                            "저는 여러분과 함께 배우고 성장하며, 이 재정적인 모험을 함께할 수 있어 기쁨을 느낍니다. 함께 여행하는 동안 서로에게 영감을 주고 받으며, 금융의 감동적인 순간들을 만들어 나가고 싶습니다.\n", LocalDateTime.now());
            em.persist(mentee);

        }

        public void dbInit4() {
            Mentee mentee = createMentee("kau21@naver.com", "5555", "Michel", "01055556666", Gender.MAN,
                    "대출", 26, "학생", 4, 2, 2, 4, "안녕하세요! 지식의 즐거움과 전문성 강화에 열중하는 Michel입니다. 대학과 금융 분야에서의 탐험을 통해 나만의 진로를 발견하고 있는데요.\n" +
                            "내가 빠져 있는 영역은 주식시장과 금융기술(Fintech)입니다. 특히 주식 시장의 다양한 흐름과 동향에 대한 탐구심을 가지고 있어, 기존 투자 전략을 현대적이고 효율적인 방식으로 혁신하고자 합니다. 또한 금융기술(Fintech)의 발전이 금융 분야에 미치는 영향에 대한 지속적인 연구를 통해, 디지털 금융 혁명에 어떻게 기여할 수 있을지에 대한 열망을 품고 있습니다.\n" +
                            "학문적인 학습과 다양한 경험을 바탕으로, 금융의 다양한 측면을 이해하고자 끊임없이 노력하고 있습니다. 여러분과 함께 이 길에서 배우고 성장하며, 금융의 새로운 지평을 열어나가고 싶어졌습니다.\n", LocalDateTime.now());
            em.persist(mentee);


        }

        public void dbInit5() {
            Mentee mentee = createMentee("kau22@naver.com", "6666", "Robert", "01066667777", Gender.MAN,
                    "부동산", 27, "학생", 5, 3, 4, 3, "안녕하세요, 여러분! 저는 Robert라고 합니다. 현재 기업 금융과 투자 분야를 새롭게 탐험하고 있는 여행자입니다.\n" +
                            "제 성격은 학습에 대한 강한 의욕과 진로개발에 대한 열정이 두드러지는데요, 새로운 분야에서의 도전에 대한 열망이 항상 저를 움직이게 만듭니다.\n" +
                            "기업금융과 투자 분야에서 보람 있는 경력을 쌓고 싶은 간절한 열망이 저를 이곳에 세우게 했습니다. 특히, 기업 재무, 재무 모델링, 그리고 투자 의사 결정의 복잡한 기술에 대한 깊은 관심을 가지고 있습니다.\n" +
                            "대학생으로서 새로운 도전을 향해 나아가고자 여러분과 함께 배우고 성장하고 싶습니다. 이 자리에서 함께 복잡한 금융의 세계를 탐험하며, 서로에게 영감을 주고 받으며 발전해 나가는 여정을 함께하고 싶습니다.\n", LocalDateTime.now());

            em.persist(mentee);

        }

        public void dbInit6() {
            Mentee mentee = createMentee("kau23@naver.com", "6666", "Olivia", "01066667777", Gender.WOMAN,
                    "부동산", 27, "학생", 5, 3, 4, 3, "안녕하세요! 저는 Olivia, 호기심 가득한 여행을 즐기며 끊임없이 새로운 것을 배우는 것에 즐거움을 느끼고 있어요.\n" +
                            "현재 대학생활의 다채로운 경험을 즐기며, 기업금융과 투자 분야에서의 흥미로운 커리어를 꿈꾸고 있습니다. 기업 재무, 재무 모델링, 그리고 전략적 투자 결정에 필요한 복잡한 기술에 대한 이해와 열정을 가지고 있어요.\n" +
                            "다양한 기회를 열렬히 환영하며, 금융계의 다양한 영역을 탐험하고 싶어졌습니다. 특히 부동산 투자 분야에 대한 흥미와 깊은 관심을 품고 있어, 부동산 시장의 동향과 투자 전략에 대한 지식을 향상시키고자 합니다.\n", LocalDateTime.now());

            em.persist(mentee);

        }

        //        public void dbInit7() {
//            Mentee mentee = createMentee("kau24@naver.com", "6666", "Aiden", "01066667777", Gender.MAN,
//                    "대출", 30, "기타", 5, 3, 4, 3, "저는 금융에 대한 깊은 흥미와 재테크(financial technology), 투자에 대한 강한 관심을 가진 개인입니다. 신중한 성향과 강한 목표 의식을 바탕으로 일상의 금융 의사결정에 있어 신중하게 접근하고, 목표를 향해 노력하는 것을 중요하게 여깁니다.\n" +
//                            "금융 멘토링 프로그램에 참여함으로써, 전문가의 지도 아래에서 더욱 효과적인 재테크(financial technology) 전략과 투자 방법을 습득하고자 합니다. 목표 지향적인 성격을 가지고 있어 멘토링을 통해 성장의 기회를 극대화하고, 금융적인 안목을 높여 미래의 재무적 안정성을 구축하고자 합니다.\n" +
//                            "더불어, 다양한 금융 상품과 시장 동향에 대한 이해를 향상시키며, 개인적인 금융 목표를 달성하기 위해 노력하고 있습니다. 멘토와의 소통과 지도를 통해 나만의 투자 철학을 발전시키고, 금융적인 지식을 심화해 나가고자 하는 목표를 가지고 있습니다.\n" +
//                            "제 경험과 노력을 통해 멘토링 프로그램을 통해 제 금융적인 역량을 높이고, 미래를 위한 지혜를 쌓아가는데 적극 참여하겠습니다. 감사합니다.\n", LocalDateTime.now());
//
//            em.persist(mentee);
//
//        }



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