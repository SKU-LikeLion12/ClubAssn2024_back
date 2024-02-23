package likelion12.puzzle.service;

import likelion12.puzzle.domain.Event;
import likelion12.puzzle.domain.JoinEvent;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.repository.EventRepository;
import likelion12.puzzle.repository.JoinEventRepository;
import likelion12.puzzle.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinEventService {

    private final JoinEventRepository joinEventRepository;
    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;


    // 내가 참여한 이벤트 하나 이벤트 id와 studentId 로 조회
    public JoinEvent findJoinEvent(int studentId, Long eventId) { // 호주가 Long으로 바꾸면 매개변수 자료형 Long으로 바꿔야함
        Member member = memberRepository.findByStudentId(studentId);
        Event event = eventRepository.findByEventId(eventId);
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
    public JoinEvent saveJoinEvent(int studentId, Long eventId) { // 호주가 Long으로 바꾸면 매개변수 자료형 Long으로 바꿔야함
        Member member = memberRepository.findByStudentId(studentId);
        Event event = eventRepository.findByEventId(eventId);
        JoinEvent joinEvent = joinEventRepository.addJoinEvent(member, event);
        return joinEvent;

    }

    // (관리자용) 멤버의 이벤트(퍼즐) 삭제 => 잘못 넣었을 경우
    // 관리자 인증 로직 추가해야함
    @Transactional
    public boolean removeJoinEvent(int studentId, Long eventId) {// 호주가 Long으로 바꾸면 매개변수 자료형 Long으로 바꿔야함
        JoinEvent joinEvent = findJoinEvent(studentId, eventId);
        if (joinEvent != null) {
            return true;
        } else {
            return false;
        }
    }


}
