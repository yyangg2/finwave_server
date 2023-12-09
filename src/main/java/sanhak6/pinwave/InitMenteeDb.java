//package sanhak6.pinwave;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import sanhak6.pinwave.domain.Gender;
//import sanhak6.pinwave.domain.Mentee;
//import sanhak6.pinwave.domain.MenteeMentor;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import java.time.LocalDateTime;
//
//@Component
//@RequiredArgsConstructor
//public class InitMenteeDb {
//    private final InitService initService;
//
//    @PostConstruct
//    public void init() {
//        initService.dbInit1();
//        initService.dbInit2();
//        initService.dbInit3();
//        initService.dbInit4();
//        initService.dbInit5();
//        initService.dbInit6();
//    }
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService {
//
//        private final EntityManager em;
//
//        public void dbInit1() {
//            Mentee mentee = createMentee("kau20@naver.com", "4444", "Sarah", "01044445555", Gender.WOMAN,
//                    "주식", 22, "학생", 3, 1, 1, 1, "안녕하세요, 여러분! 저는 금융의 무한한 가능성을 탐험하는 여정에 도전하고 있는 Sarah입니다." +
//                            "제 성격은 호기심과 열정이 가득한데요, 금융 세계에서 새로운 지식을 습득하고 다양한 경험을 통해 성장하는 것에 큰 흥미를 느낍니다." +
//                            "교육을 통한 재정적인 안정과 미래 투자에 대한 심사숙고가 제 가치 중 하나입니다. 재무적 기본을 탄탄히 다지고, 저축 습관을 꾸준히 기르며 투자의 세계로 나아가는 여정에서 끊임없는 호기심을 가지고 있습니다." +
//                            "저는 여러분과 함께 배우고 성장하며, 이 재정적인 모험을 함께할 수 있어 기쁨을 느낍니다. 함께 여행하는 동안 서로에게 영감을 주고 받으며, 금융의 감동적인 순간들을 만들어 나가고 싶습니다.", LocalDateTime.now());
//            em.persist(mentee);
//
////            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentee(mentee);
////            em.persist(menteeMentor);
//
//
//        }
//
//        public void dbInit2() {
//            Mentee mentee = createMentee("kau21@naver.com", "5555", "Michel", "01055556666", Gender.MAN,
//                    "대출", 26, "학생", 4, 2, 2, 4, "안녕하세요! 지식의 즐거움과 전문성 강화에 열중하는 Michel입니다. 대학과 금융 분야에서의 탐험을 통해 나만의 진로를 발견하고 있는데요." +
//                            "내가 빠져 있는 영역은 주식시장과 금융기술(Fintech)입니다. 특히 주식 시장의 다양한 흐름과 동향에 대한 탐구심을 가지고 있어, 기존 투자 전략을 현대적이고 효율적인 방식으로 혁신하고자 합니다. 또한 금융기술의 발전이 금융 분야에 미치는 영향에 대한 지속적인 연구를 통해, 디지털 금융 혁명에 어떻게 기여할 수 있을지에 대한 열망을 품고 있습니다." +
//                            "학문적인 학습과 다양한 경험을 바탕으로, 금융의 다양한 측면을 이해하고자 끊임없이 노력하고 있습니다. 여러분과 함께 이 길에서 배우고 성장하며, 금융의 새로운 지평을 열어나가고 싶어졌습니다.", LocalDateTime.now());
//            em.persist(mentee);
//
////            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentee(mentee);
////            em.persist(menteeMentor);
//
//        }
//
//        public void dbInit3() {
//            Mentee mentee = createMentee("kau22@naver.com", "6666", "Robert", "01066667777", Gender.MAN,
//                    "부동산", 27, "학생", 5, 3, 4, 3, "안녕하세요, 여러분! 저는 Robert라고 합니다. 현재 기업 금융과 투자 분야를 새롭게 탐험하고 있는 여행자입니다." +
//                            "제 성격은 학습에 대한 강한 의욕과 진로개발에 대한 열정이 두드러지는데요, 새로운 분야에서의 도전에 대한 열망이 항상 저를 움직이게 만듭니다." +
//                            "기업금융과 투자 분야에서 보람 있는 경력을 쌓고 싶은 간절한 열망이 저를 이곳에 세우게 했습니다. 특히, 기업 재무, 재무 모델링, 그리고 투자 의사 결정의 복잡한 기술에 대한 깊은 관심을 가지고 있습니다." +
//                            "대학생으로서 새로운 도전을 향해 나아가고자 여러분과 함께 배우고 성장하고 싶습니다. 이 자리에서 함께 복잡한 금융의 세계를 탐험하며, 서로에게 영감을 주고 받으며 발전해 나가는 여정을 함께하고 싶습니다.", LocalDateTime.now());
//
//            em.persist(mentee);
//
////            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentee(mentee);
////            em.persist(menteeMentor);
//        }
//
//        public void dbInit4() {
//            Mentee mentee = createMentee("kau23@naver.com", "6666", "Olivia", "01066667777", Gender.WOMAN,
//                    "부동산", 27, "학생", 5, 3, 4, 3, "안녕하세요! 저는 Olivia, 호기심 가득한 여행을 즐기며 끊임없이 새로운 것을 배우는 것에 즐거움을 느끼고 있어요." +
//                            "현재 대학생활의 다채로운 경험을 즐기며, 기업금융과 투자 분야에서의 흥미로운 커리어를 꿈꾸고 있습니다. 기업 재무, 재무 모델링, 그리고 전략적 투자 결정에 필요한 복잡한 기술에 대한 이해와 열정을 가지고 있어요." +
//                            "다양한 기회를 열렬히 환영하며, 금융계의 다양한 영역을 탐험하고 싶어졌습니다. 특히 부동산 투자 분야에 대한 흥미와 깊은 관심을 품고 있어, 부동산 시장의 동향과 투자 전략에 대한 지식을 향상시키고자 합니다.", LocalDateTime.now());
//
//            em.persist(mentee);
//
////            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentee(mentee);
////            em.persist(menteeMentor);
//        }
//
////        public void dbInit5() {
////            Mentee mentee = createMentee("kau24@naver.com", "6666", "박덕배", "01066667777", Gender.MAN,
////                    "대출", 30, "기타", 5, 3, 4, 3, "저는 금융에 대한 깊은 흥미와 재테크, 투자에 대한 강한 관심을 가진 개인입니다. 신중한 성향과 강한 목표 의식을 바탕으로 일상의 금융 의사결정에 있어 신중하게 접근하고, 목표를 향해 노력하는 것을 중요하게 여깁니다.\n" +
////                            "금융 멘토링 프로그램에 참여함으로써, 전문가의 지도 아래에서 더욱 효과적인 재테크 전략과 투자 방법을 습득하고자 합니다. 목표 지향적인 성격을 가지고 있어 멘토링을 통해 성장의 기회를 극대화하고, 금융적인 안목을 높여 미래의 재무적 안정성을 구축하고자 합니다.\n" +
////                            "더불어, 다양한 금융 상품과 시장 동향에 대한 이해를 향상시키며, 개인적인 금융 목표를 달성하기 위해 노력하고 있습니다. 멘토와의 소통과 지도를 통해 나만의 투자 철학을 발전시키고, 금융적인 지식을 심화해 나가고자 하는 목표를 가지고 있습니다.\n" +
////                            "제 경험과 노력을 통해 멘토링 프로그램을 통해 제 금융적인 역량을 높이고, 미래를 위한 지혜를 쌓아가는데 적극 참여하겠습니다. 감사합니다.\n", LocalDateTime.now());
////
////            em.persist(mentee);
////
//////            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentee(mentee);
//////            em.persist(menteeMentor);
////        }
//
//        public void dbInit5() {
//            Mentee mentee = createMentee("kau25@naver.com", "6666", "James", "01066667777", Gender.MAN,
//                    "주식", 29, "기타", 5, 3, 4, 3, "안녕하세요, 금융 멘토링 프로그램에 참여하게 된 저는 항상 금융 세계에 대한 탐구와 야망을 품은 열정적인 개인입니다. 금융의 복잡성에 도전하고자 하는 강한 욕망과 함께, 미래를 위해 지속적으로 투자하고 싶어하는 목표를 갖고 있습니다." +
//                            "저는 리스크 관리와 금융 안전성을 중시하며, 이를 바탕으로 효율적이고 안정적인 투자 전략을 개발하고자 노력하고 있습니다. 또한, 다양한 금융 상품과 시장 동향에 대한 지식을 확장하여 최신 동향을 반영한 결정을 내릴 수 있도록 노력하고 있습니다." +
//                            "멘토와의 소통을 통해 나만의 투자 철학을 더욱 세련되게 발전시키고, 금융적인 안목을 높여 나가고자 합니다. 멘토의 지혜와 지도 아래에서 더 나은 금융적 안정성을 달성하기 위해 열심히 노력할 것을 약속드립니다. 감사합니다.", LocalDateTime.now());
//
//            em.persist(mentee);
//
////            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentee(mentee);
////            em.persist(menteeMentor);
//        }
//
//        public void dbInit6() {
//            Mentee mentee = createMentee("kau26@naver.com", "6666", "Bailey", "01066667777", Gender.MAN,
//                    "대출", 50, "기타", 5, 3, 4, 3, "저는 금융에 대한 흥미와 재테크, 투자에 대한 강한 관심을 가진 개인으로, 적극적이고 목표 지향적인 성격을 가지고 있습니다. 일상의 금융 의사결정에 있어서는 신중한 성향으로 접근하며, 목표를 달성하기 위해 노력하는 것을 중요하게 생각합니다." +
//                            "금융 멘토링 프로그램에 참여하면서 전문가의 지도를 받아 더욱 효과적인 재테크 전략과 투자 방법을 습득하고자 하는 목표를 가지고 있습니다. 다양한 금융 상품과 시장 동향에 대한 이해를 높이며, 개인적인 금융 목표를 달성하기 위해 노력하고 있습니다. 목표 지향적인 성격을 바탕으로 멘토링을 통해 성장의 기회를 극대화하고, 금융적인 안목을 높여 미래의 재무적 안정성을 구축하고자 합니다." +
//                            "멘토와의 소통과 지도를 통해 나만의 투자 철학을 더욱 발전시키고, 금융적인 지식을 심화해 나가고자 하는 목표를 가지고 있습니다. 제 경험과 노력을 통해 멘토링 프로그램을 통해 제 금융적인 역량을 향상시키고, 미래를 위한 지혜를 쌓아가는데 적극적으로 참여하겠습니다. 감사합니다.", LocalDateTime.now());
//
//            em.persist(mentee);
//
////            MenteeMentor menteeMentor = MenteeMentor.createMenteeMentorMentee(mentee);
////            em.persist(menteeMentor);
//        }
//
//        private Mentee createMentee(String email, String password, String name, String phone, Gender gender, String goal, Integer age,
//                                    String job, Integer count, Integer menteeRank, Integer getReviewCount, Integer doReviewCount, String introduce,
//                                    LocalDateTime createDate) {
//            Mentee mentee = new Mentee();
//            mentee.setEmail(email);
//            mentee.setPassword(password);
//            mentee.setName(name);
//            mentee.setPhone(phone);
//            mentee.setGender(gender);
//            mentee.setGoal(goal);
//            mentee.setAge(age);
//            mentee.setJob(job);
//            mentee.setCount(count);
//            mentee.setMenteeRank(menteeRank);
//            mentee.setGetReviewCount(getReviewCount);
//            mentee.setDoReviewCount(doReviewCount);
//            mentee.setIntroduce(introduce);
//            mentee.setCreateDate(createDate);
//
//            return mentee;
//        }
//
//    }
//}
