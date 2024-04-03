package likelion12.puzzle.service;

import likelion12.puzzle.DTO.JoinClubDTO;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.domain.JoinClub;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.repository.JoinClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinClubService {
    private final JoinClubRepository joinClubRepository;
    private final ClubService clubService;
    private final MemberService memberService;

    // 동아리에 새로운 학생 추가
    @Transactional
    public JoinClub saveNewMember(String studentId, String studentName, String clubName) {
        Club iconClub = clubService.findByName(clubName);

        Member member = new Member(studentId, studentName, iconClub);
        JoinClub joinClub = new JoinClub(iconClub, member);

        return joinClubRepository.saveNewMemberForClub(joinClub);
    }

    // 기존 학생, 기존 동아리
    @Transactional
    public JoinClub saveNewMember(String studentId, String clubName) {
        Member member = memberService.findByStudentId(studentId);
        Club club = clubService.findByName(clubName);
        JoinClub joinClub = new JoinClub(club, member);

        return joinClubRepository.saveNewMemberForClub(joinClub);
    }

    // 동아리에 가입된 학생들 찾기
    public List<JoinClub> findAllByClubName(String clubName) {
        Club club = clubService.findByName(clubName);
        return joinClubRepository.findAllByClubName(club);
    }

    // 학번으로 어느 동아리 가입되어있는지 조회
    public List<JoinClub> findByStudentId(String studentId) {
        return joinClubRepository.findByMemberId(studentId);
    }

    // 동아리에서 학생 탈퇴
    @Transactional
    public boolean deleteMember(Member student, String clubName) {
        Club club = clubService.findByName(clubName);
        JoinClub joinClub = joinClubRepository.findJoinClub(club, student);

        return joinClubRepository.deleteJoinClub(joinClub);
    }

    // 동아리원 검색
    public List<JoinClubDTO> searchByKeyword(String keyword) {
        return joinClubRepository.findCMManageByKeyword(keyword);
    }
}
