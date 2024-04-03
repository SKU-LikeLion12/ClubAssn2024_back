package likelion12.puzzle.service;

import likelion12.puzzle.domain.Event;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;
    private final MemberService memberService;


    // 이벤트 추가
    @Transactional
    public Event addEvent(String name, String image, LocalDateTime date) {
        Event event = eventRepository.addEvent(name, image, date);
        return event;
    }

    // 이벤트 삭제
    @Transactional
    public boolean removeEvent(Long eventId) {
        Event event = eventRepository.findByEventId(eventId);
        boolean result = eventRepository.removeEvent(event);
        if (!result) {
            return false;
        } else {
            return true;
        }
    }

    // 모든 이벤트 조회
    public List<Event> findAllEvents() {
        return eventRepository.findAllEvents();
    }

    // 이벤트 이름으로 이벤트 찾기
    public Event findByEventName(String eventName) {
        Event event = eventRepository.findByEventName(eventName);
        if (event != null) {
            return event;
        } else {
            return null;
        }
    }

    // 이벤트 id로 이벤트 찾기
    public Event findByEventId(Long eventId) {
        Event event = eventRepository.findByEventId(eventId);
        if (event != null) {
            return event;
        } else {
            return null;
        }
    }

    // 내가 참여한 행사 리스트 반환 (id로)
    public List<Event> findAllEvents(String studentId) {
        Member member = memberService.findByStudentId(studentId);
        List<Event> eventList = eventRepository.findAllPartEventsExceptImage(member);

        if (eventList.isEmpty()) {
            return null;
        } else {
            return eventList;
        }
    }

    // (불용) 동명이인 있을 수 있음.
    // 내가 참여한 행사 리스트 반환 (name 으로)
//    public List<Event> findAllEvents(String memberName) {
//        Member member = memberRepository.findByName(memberName);
//        List<Event> eventList = eventRepository.findAllEvents(member);
//        if (eventList != null) {
//            return eventList;
//        } else {
//            return null;
//        }
//    }

}
