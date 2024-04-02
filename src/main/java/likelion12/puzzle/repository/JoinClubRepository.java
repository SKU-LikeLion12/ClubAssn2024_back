package likelion12.puzzle.repository;

import jakarta.persistence.EntityManager;
import likelion12.puzzle.DTO.JoinClubDTO;
import likelion12.puzzle.DTO.MemberClubDTO;
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

    // iconClub이 null이면 해당 멤버의 club중 하나 세팅해야함 (위에 함수랑 다르게 club을 반환함.)
    public List<Club> findByStudentIdClub(String studentId){
        return em.createQuery("SELECT jc.club FROM JoinClub jc WHERE jc.member.studentId =:studentId", Club.class)
                .setParameter("studentId", studentId)
                .getResultList();
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

    // 동아리원 검색
    public List<JoinClubDTO> findCMManageByKeyword(String keyword) {
        return em.createQuery("SELECT new likelion12.puzzle.DTO.JoinClubDTO(m.studentId, m.name, c.name) " +
                        "FROM JoinClub jc " +
                        "INNER JOIN jc.member m " +
                        "INNER JOIN jc.club c " +
                        "WHERE m.name LIKE :keyword OR m.studentId LIKE :keyword OR c.name LIKE :keyword", JoinClubDTO.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }

    // 모든 학생의 가입된 동아리 정보
    public List<MemberClubDTO> findJoinedClubsForAllMember(){
        List<Object[]> members = em.createQuery("SELECT m.id, m.name, m.studentId FROM Member m", Object[].class)
                .getResultList();
        List<MemberClubDTO> memberClubDTOs = members.stream().map(member -> {
            Long memberId = (Long) member[0];
            String name = (String) member[1];
            String studentId = (String) member[2];

            List<String> clubs = em.createQuery(
                            "SELECT c.name FROM JoinClub jc JOIN jc.club c WHERE jc.member.id = :memberId", String.class)
                    .setParameter("memberId", memberId)
                    .getResultList();
            return new MemberClubDTO(memberId, name, studentId, clubs);
        }).toList();

        return memberClubDTOs;
    }
}