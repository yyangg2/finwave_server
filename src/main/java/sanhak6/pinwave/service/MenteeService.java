package sanhak6.pinwave.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanhak6.pinwave.domain.Level;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.repository.MenteeRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenteeService {

    private final MenteeRepository menteeRepository;

    // 멘티 로그인 구현
    public Mentee loginMentee(String email, String password) {
        Mentee mentee = menteeRepository.findByEmailAndPassword(email, password);

        if (mentee == null) {
            throw new BadCredentialsException("이메일 또는 비밀번호가 잘못되었습니다.");
        }

        return mentee;
    }

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

        // Update Mentee profile fields
        mentee.setIntroduce(introduce);
        mentee.setJob(job);
        mentee.setGoal(goal);
        mentee.setKnowLevel(assetLevel);
        mentee.setRegion(region);
        mentee.setAssetLevel(assetLevel);

        menteeRepository.save(mentee);
    }

}
