package sanhak6.pinwave.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanhak6.pinwave.domain.*;
import sanhak6.pinwave.domain.review.Review;
import sanhak6.pinwave.repository.ChecklistRepository;
import sanhak6.pinwave.repository.MenteeRepository;
import sanhak6.pinwave.repository.MentorRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChecklistService {

    private final ChecklistRepository checklistRepository;
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;

    public Long join(Checklist checklist) {
        checklistRepository.save(checklist);
        return checklist.getId();
    }

    //변경 감지 사용
    public void updateMentorChecklist(Long checklistId, List<Todo> todos) {
        Checklist checklist = checklistRepository.findOne(checklistId);

        checklist.setTodos(todos);

    }

    public void updateMenteeChecklist(Long checklistId, List<Todo> todos) {
        Checklist checklist = checklistRepository.findOne(checklistId);

        checklist.setTodos(todos);

    }

    //전체 조회
    public List<Checklist> findChecklists() { return checklistRepository.findAll(); }

    //단건 조회
    public Checklist findOne(Long checklistId) { return checklistRepository.findOne(checklistId); }



    public Long Checklist(Long mentorId, Long menteeId, List<Todo> todos) {
        //엔티티 조회
        Mentor mentor = mentorRepository.findOne(mentorId);
        Mentee mentee = menteeRepository.findOne(menteeId);

        //체크리스트 생성
        Checklist checklist = Checklist.createChecklist(mentor, mentee, todos);

        //체크리스트 저장
        checklistRepository.save(checklist);

        return checklist.getId();
    }

}