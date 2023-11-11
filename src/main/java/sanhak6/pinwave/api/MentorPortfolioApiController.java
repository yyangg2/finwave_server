//package sanhak6.pinwave.api;
//
//import lombok.Data;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import sanhak6.pinwave.domain.Mentor;
//import sanhak6.pinwave.domain.review.Review;
//import sanhak6.pinwave.service.MentorService;
//
//import javax.validation.Valid;
//
//@RestController
//public class MentorPortfolioApiController {
//
//    private final MentorService mentorService;
//
//    public MentorPortfolioApiController(MentorService mentorService) {
//        this.mentorService = mentorService;
//    }
//    // 멘토 포트폴리오 열람(프로필 열람)
//    @PostMapping("/portfolio_mentor")
//    public ResponseEntity<String> addMentorPortfolio(@RequestBody @Valid MentorPortfolioRequest request) {
//        Long mentorId = request.getMentorId();
//        String introduce = request.getIntroduce();
//        String job = request.getJob();
//        String field1 = request.getField1();
//        String field2 = request.getField2();
//        String field3 = request.getField3();
//        String region = request.getRegion();
//
//        mentorService.updateMentorPortfolio(mentorId, introduce, job, field1, field2, field3, region);
//
//        return ResponseEntity.ok("멘토님의 포트폴리오가 등록되었습니다.");
//    }
//
//    @Data
//    static class MentorPortfolioRequest {
//        private Long mentorId;
//        private String introduce;
//        private String job;
//        private String field1;
//        private String field2;
//        private String field3;
//        private String region;
//    }
//
//    // 멘토 포트폴리오 열람 API(프로필 열람)
//    @GetMapping("/portfolio_mentor")
//    public ResponseEntity<MentorPortfolioResponse> getMentorPortfolio(@RequestParam Long mentorId) {
//        Mentor mentor = mentorService.findOne(mentorId);
//
//        if (mentor == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        MentorPortfolioResponse response = new MentorPortfolioResponse();
//        response.setMentorId(mentor.getId());
//        response.setIntroduce(mentor.getIntroduce());
//        response.setField1(mentor.getField1());
//        response.setField2(mentor.getField2());
//        response.setField3(mentor.getField3());
//        response.setRegion(mentor.getRegion());
//        response.setMentoringCount(mentor.getCount());
//        response.setMentorRank(mentor.getMentorRank());
//        response.setReviewMentorId(mentor.getReviews().stream()
//                .map(Review::getReviewMentor)
//                .map(Mentor::getId)
//                .findFirst()
//                .orElse(null));
//
//        return ResponseEntity.ok(response);
//    }
//
//    @Data
//    static class MentorPortfolioResponse {
//        private Long mentorId;
//        private String introduce;
//        private String field1;
//        private String field2;
//        private String field3;
//        private String region;
//        private Integer mentoringCount;
//        private Integer mentorRank;
//        private Long reviewMentorId;
//    }
//}
//
