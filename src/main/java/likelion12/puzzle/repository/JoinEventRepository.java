package likelion12.puzzle.repository;

import jakarta.persistence.EntityManager;
import likelion12.puzzle.domain.Event;
import likelion12.puzzle.domain.JoinEvent;
import likelion12.puzzle.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JoinEventRepository {

    private final EntityManager em;

    // 내가 참여한 이벤트 하나 이벤트 id와 studentId 로 조회
    public JoinEvent findJoinEvent(Member member, Event event) {
        return em.createQuery("SELECT je FROM JoinEvent je WHERE je.member = :member and je.event = :event", JoinEvent.class)
                .setParameter("member", member).setParameter("event", event).getSingleResult();
    }


    // (관리자용) 멤버에게 참여한 이벤트(퍼즐) 추가
    public JoinEvent addJoinEvent(Member member, Event event) {
        JoinEvent addEvent = new JoinEvent(member, event);
        em.persist(addEvent);
        return addEvent;
    }


    // (관리자용) 멤버의 이벤트(퍼즐) 삭제 => 잘못 넣었을 경우
    public boolean removeJoinEvent(JoinEvent joinEvent) {
//        int deleteCount = em.createQuery("DELETE FROM JoinEvent je WHERE je.member = :member and je.event = :event")
//                .setParameter("member", member).setParameter("event", event)
//                .executeUpdate();
//        if (deleteCount > 0) {
//            return true;
//        } else {
//            return false;
//        }
        em.remove(joinEvent);
        return true;
    }
}
