package sanhak6.pinwave.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanhak6.pinwave.api.MenteeApiController;
import sanhak6.pinwave.domain.Level;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.repository.MenteeRepository;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenteeService {

    private final MenteeRepository menteeRepository;

    /**
     * 회원 가입
     */
    public Long join(Mentee mentee) {

        validateDuplicateMentor(mentee);
        menteeRepository.save(mentee);
        return mentee.getId();
    }

    private void validateDuplicateMentor(Mentee mentee) {
        List<Mentee> findMentees = menteeRepository.findByEmail(mentee.getEmail());
        if (!findMentees.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Mentee loginMentee(String email, String password) {
        Mentee mentee = menteeRepository.findByEmailAndPassword(email, password);

        if (mentee == null) {
            return null;
//            throw new IllegalArgumentException("잘못된 이메일 또는 비밀번호입니다.");
        }

        return mentee;
    }

    //변경 감지 사용
    public void updateMentee(Long menteeId, String introduce) {
        Mentee mentee = menteeRepository.findOne(menteeId);

        mentee.setIntroduce(introduce);
    }

    //전체 조회
    public List<Mentee> findMentees() { return menteeRepository.findAll(); }

    //단건 조회
    public Mentee findOne(Long menteeId) { return menteeRepository.findOne(menteeId); }

    public class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }

    public void updateMenteeProfile(Long menteeId, String introduce, String job, String goal, String knowLevel, String region, String assetLevel) {
        Mentee mentee = menteeRepository.findOne(menteeId);

        if (mentee == null) {
            throw new NotFoundException("Mentee not found");
        }

        mentee.setIntroduce(introduce);
        mentee.setJob(job);
        mentee.setGoal(goal);
        mentee.setKnowLevel(assetLevel);
        mentee.setRegion(region);
        mentee.setAssetLevel(assetLevel);

        menteeRepository.save(mentee);
    }

    // 멘티 프로필 열람
    public MenteeApiController.MenteeProfileDto getMenteeProfileById(Long menteeId) {
        Mentee mentee = menteeRepository.findOne(menteeId);

        if (mentee == null) {
            throw new NotFoundException("멘티를 찾을 수 없습니다.");
        }

        return new MenteeApiController.MenteeProfileDto(mentee);
    }

}