package sanhak6.pinwave.service.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.repository.MentorRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepository mentorRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Mentor mentor) {

        validateDuplicateMentor(mentor);
        mentorRepository.save(mentor);
        return mentor.getId();
    }

    private void validateDuplicateMentor(Mentor mentor) {
        List<Mentor> findMentors = mentorRepository.findByName(mentor.getUser().getEmail());
        if (!findMentors.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //전체 조회
    public List<Mentor> findMentors() { return mentorRepository.findAll(); }

    //단건 조회
    public Mentor findOne(Long mentorId) { return mentorRepository.findOne(mentorId); }


}
