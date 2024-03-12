package likelion12.puzzle.service;

import likelion12.puzzle.DTO.JoinClubDTO;
import likelion12.puzzle.domain.Event;
import likelion12.puzzle.domain.JoinEvent;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.repository.JoinEventRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinEventService {
    private final JoinEventRepository joinEventRepository;
    private final EventService eventService;
    private final MemberService memberService;


    // 내가 참여한 이벤트 하나 이벤트 id와 studentId 로 조회
    public JoinEvent findJoinEvent(String studentId, Long eventId) {
        Member member = memberService.findByStudentId(studentId);
        Event event = eventService.findByEventId(eventId);
        JoinEvent joinEvent = joinEventRepository.findJoinEvent(member, event);
        if (joinEvent != null) {
            return joinEvent;
        } else {
            return null;
        }
    }

    // (관리자용) 멤버에게 참여한 이벤트(퍼즐) 추가
    // 관리자 인증 로직 추가해야함
    @Transactional
    public JoinEvent saveJoinEvent(String studentId, Long eventId) {
        Member member = memberService.findByStudentId(studentId);
        Event event = eventService.findByEventId(eventId);
        JoinEvent joinEvent = new JoinEvent(member, event);

        return joinEventRepository.addJoinEvent(joinEvent);
    }

    // (관리자용) 멤버의 이벤트(퍼즐) 삭제 => 잘못 넣었을 경우
    // 관리자 인증 로직 추가해야함
    @Transactional
    public boolean removeJoinEvent(String studentId, Long eventId) {
        JoinEvent joinEvent = findJoinEvent(studentId, eventId);
        joinEventRepository.removeJoinEvent(joinEvent);
        return true;
    }

    public List<JoinEvent> findAllJoinEvents(String studentId) {
        Member member = memberService.findByStudentId(studentId);
        List<JoinEvent> eventList = joinEventRepository.findAllJoinEvents(member);

        if (eventList.isEmpty()) {
            return null;
        } else {
            return eventList;
        }
    }
}