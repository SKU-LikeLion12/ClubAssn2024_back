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
    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;

    // 새로운 동아리원 추가
    public JoinClub saveNewMember(int studentId, String name, String clubName) {
        Club club = clubRepository.findByName(clubName);
        Member member = new Member(studentId, name, club);
        JoinClub joinClub = new JoinClub(club, member);

        em.persist(joinClub);
        return joinClub;
    }

    // 학번으로 가입된 동아리 찾기
    public List<JoinClub> findByMemberId(int studentId) {
        return em.createQuery("select jc.club from JoinClub jc where jc.member.studentId =:id", JoinClub.class)
                .setParameter("id", studentId).getResultList();
    }

    // 동아리에 있는 동아리원 조회
    public List<JoinClub> findAllByClubName(String clubName) {
        return em.createQuery("select jc.member from JoinClub jc where jc.club.name = :clubName", JoinClub.class)
                .setParameter("clubName", clubName).getResultList();
    }

    // 동아리에서 해당 학생 삭제
    public boolean deleteMember(int studentId, String clubName) {
//        Member member = memberRepository.findByStudentId(studentId);
//        Club club = clubRepository.findByName(clubName);

        int deleteCount = em.createQuery("delete from JoinClub jc where jc.member.studentId = :id and jc.club.name = :name")
                .setParameter("id", studentId).setParameter("name", clubName)
                .executeUpdate();
        if (deleteCount > 0) {
            return true;
        }
        return false;
    }
}