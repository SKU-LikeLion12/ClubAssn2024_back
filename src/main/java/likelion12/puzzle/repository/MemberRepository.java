package likelion12.puzzle.repository;

import jakarta.persistence.EntityManager;
import likelion12.puzzle.DTO.JoinClubDTO;
import likelion12.puzzle.DTO.MemberClubDTO;

import likelion12.puzzle.DTO.MemberDTO;
import likelion12.puzzle.DTO.MemberDTO.MemberInfo;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.domain.JoinClub;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.exception.MemberLoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    // 새로운 학생 추가
    public void addNewMember(Member member) {
        em.persist(member);
    }

    // 기본키로 조회
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    // 학번으로 학생 조회(학생이 없을 때 경우 수정)
    public Member findByStudentId(String studentId) {
        try {
            return em.createQuery("select m from Member m where m.studentId =:id", Member.class)
                    .setParameter("id", studentId).getSingleResult();
        } catch (Exception e) {
            return null;
        }
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

    public List<MemberInfo> findByKeyword(String keyword) {
        return em.createQuery("SELECT new MemberInfo(m.studentId, m.name) " +
                        "FROM Member m " +
                        "WHERE m.name LIKE :keyword OR m.studentId LIKE :keyword", MemberInfo.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }
}