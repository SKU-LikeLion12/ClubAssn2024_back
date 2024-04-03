package likelion12.puzzle.repository;

import jakarta.persistence.EntityManager;
import likelion12.puzzle.domain.Event;
import likelion12.puzzle.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static likelion12.puzzle.DTO.EventDTO.*;

@Repository
@RequiredArgsConstructor
public class EventRepository {

    private final EntityManager em;

    // 이벤트 추가
    public Event addEvent(Event event) {
        em.persist(event);
        return event;
    }

    // 이벤트 삭제
    public boolean removeEvent(Event event) {
        em.remove(event);
        return true;
    }

    // 모든 이벤트 조회
    public List<Event> findAllEvents() {
        return em.createQuery("SELECT e FROM Event e", Event.class)
                .getResultList();
    }

    // 이벤트 이름으로 이벤트 찾기
    public Event findByEventName(String eventName) {
        return em.createQuery("SELECT e FROM Event e WHERE e.name = :EventName", Event.class)
                .setParameter("EventName", eventName).getSingleResult();
    }

    // 이벤트 id로 이벤트 찾기
    public Event findByEventId(Long eventId) {
        return em.createQuery("SELECT e FROM Event e WHERE e.id = :EventId", Event.class)
                .setParameter("EventId", eventId).getSingleResult();
    }

    // 내가 참여한 행사 리스트 반환(하나씩 반환하는 디자인이 없어서 한번에 반환하는 코드만 필요할듯) + 참여 안한 이벤트들만 나오게(이미지 빼고)
    public List<Event> findAllPartEventsExceptImage(Member member) {
        return em.createQuery("SELECT je.event FROM JoinEvent je WHERE je.member = :member", Event.class)
                .setParameter("member", member).getResultList();
    }

    public List<EventAllRequestExceptImage> findAllExceptImage() {

        return em.createQuery("SELECT new EventAllRequestExceptImage(e.id, e.name, e.date) " +
                    "FROM Event e", EventAllRequestExceptImage.class)
                .getResultList();
    }
}
