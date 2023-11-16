package sanhak6.pinwave.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanhak6.pinwave.api.MentorApiController;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.repository.MentorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MentorService {

    @PersistenceContext
    private EntityManager em;
    private final MentorRepository mentorRepository;


    public Mentor loginMentor(String email, String password) {
        Mentor mentor = mentorRepository.findByEmailAndPassword(email, password);

        if (mentor == null) {
            throw new BadCredentialsException("이메일 또는 비밀번호가 옳지 않습니다.");
        }

        return mentor;
    }

    /**
     * 회원 가입
     */
    public Long join(Mentor mentor) {

        validateDuplicateMentor(mentor); //중복 회원 검증
        mentorRepository.save(mentor);
        return mentor.getId();
    }

    private void validateDuplicateMentor(Mentor mentor) {
        List<Mentor> findMentors = mentorRepository.findByEmail(mentor.getEmail());
        if (!findMentors.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //변경 감지 사용
    public void updateMentor(Long mentorId, String introduce) {
        Mentor mentor = mentorRepository.findOne(mentorId);

        mentor.setIntroduce(introduce);
    }

    //전체 조회
    public List<Mentor> findMentors() {
        return mentorRepository.findAll();
    }

    //단건 조회
    public Mentor findOne(Long mentorId) {
        return mentorRepository.findOne(mentorId);
    }


    // 로그인 예외처리
    public static class NotFoundException extends Throwable {
        public NotFoundException(String message) {
            super(message);
        }
    }

    // 멘토 포트폴리오 등록 서비스
    // 멘토의 포트폴리오 업데이트 메서드
    public void updateMentorPortfolio(Long mentorId, String introduce, String career, String job, String field1, String field2, String field3, String region) throws NotFoundException {
        Mentor mentor = mentorRepository.findOne(mentorId);

        if (mentor == null) {
            throw new NotFoundException("멘토를 찾을 수 없습니다.");
        }

        // 멘토의 포트폴리오 정보 업데이트
        mentor.setIntroduce(introduce); // 소개
        mentor.setCareer(career); // 경력
        mentor.setJob(job); // 직업
        mentor.setField1(field1);
        mentor.setField2(field2);
        mentor.setField3(field3);
        mentor.setRegion(region);

        // 변경 사항 저장
        mentorRepository.save(mentor);
    }

    public MentorApiController.MentorPortfolioDto getMentorPortfolioById(Long mentorId) throws NotFoundException {
        Mentor mentor = mentorRepository.findOne(mentorId);

        if (mentor == null) {
            throw new NotFoundException("멘토를 찾을 수 없습니다.");
        }

        return new MentorApiController.MentorPortfolioDto(mentor);
    }

    public List<Mentor> searchMentorsByFilter(String career, String job, String region, String field1, String field2, String field3) {
        return mentorRepository.findByFiltering(
                career, job, region, field1, field2, field3);
    }

}

