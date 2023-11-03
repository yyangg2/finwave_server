package sanhak6.pinwave.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.repository.MenteeRepository;
import sanhak6.pinwave.repository.MentorRepository;

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
        List<Mentee> findMentees = menteeRepository.findByName(mentee.getEmail());
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
}
