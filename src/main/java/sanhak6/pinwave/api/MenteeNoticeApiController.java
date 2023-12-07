//package sanhak6.pinwave.api;
//
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import sanhak6.pinwave.domain.Mentee;
//import sanhak6.pinwave.domain.Mentor;
//import sanhak6.pinwave.domain.Notice;
//import sanhak6.pinwave.domain.NoticeType;
//import sanhak6.pinwave.domain.review.Review;
//import sanhak6.pinwave.domain.review.ReviewMentee;
//import sanhak6.pinwave.repository.MenteeRepository;
//import sanhak6.pinwave.repository.NoticeRepository;
//import sanhak6.pinwave.service.MenteeService;
//import sanhak6.pinwave.service.NoticeService;
//
//import javax.validation.Valid;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static java.util.stream.Collectors.toList;
//
//@RestController
//@RequiredArgsConstructor
//public class MenteeNoticeApiController {
//    private final MenteeService menteeService;
//    private final MenteeRepository menteeRepository;
//    private final NoticeService noticeService;
//    private final NoticeRepository noticeRepository;
//
//
//    /**
//     * 등록 API
//     */
//    @PostMapping("/notice/mentee/{id}")
//    public CreateMenteeReviewResponse saveReviewMentee(@PathVariable("mentorId") Long mentorId, @PathVariable("menteeId") Long menteeId, @RequestBody @Valid MenteeReviewApiController.CreateMenteeReviewRequest request) {
//        ReviewMentee reviewMentee = new ReviewMentee();
//        Mentee findMentee = menteeService.findOne(menteeId);
//        Mentor findMentor = mentorService.findOne(mentorId);
//
//        reviewMentee.setContent(request.getContent());
//        reviewMentee.setStar(request.getStar());
//        reviewMentee.setCreateDate(request.getCreateDate());
//        reviewMentee.setReviewMentee(findMentee);
//        reviewMentee.setReviewMentor(findMentor);
//
////        Long id = reviewService.join(reviewMentee, findMentee, findMentor);
//        Long id = reviewService.join(reviewMentee);
////        return new CreateMenteeReviewResponse(id, menteeId, mentorId);
//        return new MenteeReviewApiController.CreateMenteeReviewResponse(id, findMentee.getId(), findMentor.getId());
//    }
//
//    /**
//     * 조회 API
//     */
//    @GetMapping("/notice/mentee/{id}")
//    public List<NoticeDto> menteeNotices(@PathVariable("id") Long id) {
//        Mentee findMentee = menteeService.findOne(id);
//        List<Notice> notices = noticeRepository.findAllWithTodo();
//        List<NoticeDto> result = notices.stream()
//                .map(n -> new NoticeDto(n))
//                .collect(toList());
//
//        return result;
//    }
//
//    @Data
//    static class NoticeDto {
//        private Long noticeId;
//        private String content;
//        private LocalDateTime sendDate;
//        private NoticeType noticeType;
//        private String menteeName;
//        private String mentorName;
//
//
//        public NoticeDto(Notice notice) {
//            noticeId = notice.getId();
//            content = notice.getContent();
//            sendDate = notice.getSendDate();
//            noticeType = notice.getNoticeType();
//            menteeName = notice.getNoticeMentee().getName();
//            mentorName = notice.getNoticeMentor().getName();
//        }
//    }
//
//    @Data
//    static class CreateNoticeRequest {
//        private String content;
//        private NoticeType noticeType;
//        private LocalDateTime sendDate;
//    }
//
//    @Data
//    static class CreateNoticeResponse {
//        private Long id;
//        private Long menteeId;
//        private Long mentorId;
//
//        public CreateMenteeReviewResponse(Long id, Long menteeId, Long mentorId) {
//            this.id = id;
//            this.menteeId = menteeId;
//            this.mentorId = mentorId;
//        }
//
//    }
//
//}
