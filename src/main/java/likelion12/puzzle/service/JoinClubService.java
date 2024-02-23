package likelion12.puzzle.service;

import likelion12.puzzle.domain.Club;
import likelion12.puzzle.domain.JoinClub;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.repository.ClubRepository;
import likelion12.puzzle.repository.JoinClubRepository;
import likelion12.puzzle.repository.MemberRepository;
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
    public JoinClub saveNewMember(int studentId, String studentName, String clubName) {
        return joinClubRepository.saveNewMember(studentId, studentName, clubName);
    }

    // 동아리에 가입된 학생들 찾기
    public List<JoinClub> findAllByClubName(String clubName) {
        return joinClubRepository.findAllByClubName(clubName);
    }

    // 학번으로 어느 동아리 가입되어있는지 조회
    public List<JoinClub> findByStudentId(int studentId) {
        return joinClubRepository.findByMemberId(studentId);
    }

    // 동아리에서 학생 탈퇴
    @Transactional
    public boolean deleteMember(int studentId, String clubName) {
        JoinClub joinClub = joinClubRepository.findJoinClub(clubName, studentId);

        return joinClubRepository.deleteJoinClub(joinClub);
    }
}
