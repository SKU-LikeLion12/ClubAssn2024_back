package likelion12.puzzle.repository;

import jakarta.persistence.EntityManager;
import likelion12.puzzle.domain.Event;
import likelion12.puzzle.domain.JoinEvent;
import likelion12.puzzle.domain.Member;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public JoinEvent addJoinEvent(JoinEvent joinEvent) {
        em.persist(joinEvent);
        return joinEvent;
    }

    // (관리자용) 멤버의 이벤트(퍼즐) 삭제 => 잘못 넣었을 경우
    public boolean removeJoinEvent(JoinEvent joinEvent) {
        em.remove(joinEvent);
        return true;
    }

    // 학번으로 참여한이벤트 찾기(이벤트 추가할 때 추가된 시간 넣어야하니까)
    public List<JoinEvent> findAllJoinEvents(Member member) {
        return em.createQuery("SELECT je.event FROM JoinEvent je WHERE je.member = :member", JoinEvent.class)
                .setParameter("member", member).getResultList();
    }
}
