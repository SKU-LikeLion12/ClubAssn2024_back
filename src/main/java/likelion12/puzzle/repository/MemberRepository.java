package likelion12.puzzle.repository;

import jakarta.persistence.EntityManager;
import likelion12.puzzle.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    // 학번으로 학생 조회
    public Member findByStudentId(int studentId) {
        return em.createQuery("select m from Member m where m.studentId =:id", Member.class)
                .setParameter("id", studentId).getSingleResult();
    }

    // 이름으로 학생 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name =:name", Member.class)
                .setParameter("name", name).getResultList();
    }

    // 전체 학생 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 학생 삭제
    public boolean deleteMember(Member member) {
        em.remove(member);
        return true;
    }
}