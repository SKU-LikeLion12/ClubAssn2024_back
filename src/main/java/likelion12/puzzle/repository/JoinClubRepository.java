package likelion12.puzzle.repository;

import jakarta.persistence.EntityManager;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.domain.JoinClub;
import likelion12.puzzle.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JoinClubRepository {
    private final EntityManager em;

    // 동아리원 추가
    public JoinClub saveNewMemberForClub(JoinClub joinClub) {
        em.persist(joinClub);
        return joinClub;
    }

    // 학번으로 가입된 동아리 찾기
    public List<JoinClub> findByMemberId(String studentId) {
        return em.createQuery("select jc.club from JoinClub jc where jc.member.studentId =:id", JoinClub.class)
                .setParameter("id", studentId).getResultList();
    }

    // 동아리에 있는 동아리원 조회
    public List<JoinClub> findAllByClubName(Club club) {
        return em.createQuery("select jc.member from JoinClub jc where jc.club = :club", JoinClub.class)
                .setParameter("club", club).getResultList();
    }

    // 동아리 이름과 학번으로 찾기
    public JoinClub findJoinClub(Club club, Member student) {
        JoinClub joinClub = em.createQuery("select jc from JoinClub jc where jc.member = :student and jc.club = :club", JoinClub.class)
                .setParameter("student", student).setParameter("club", club)
                .getSingleResult();

        return joinClub;
    }

    // 동아리에서 해당 학생 삭제
    public boolean deleteJoinClub(JoinClub joinClub) {
        em.remove(joinClub);

        return true;
    }

}