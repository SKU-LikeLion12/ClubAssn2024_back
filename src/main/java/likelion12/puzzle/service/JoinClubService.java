package likelion12.puzzle.service;

import likelion12.puzzle.DTO.JoinClubDTO;
import likelion12.puzzle.DTO.MemberClubDTO.*;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.domain.JoinClub;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.exception.NoJoinedClubException;
import likelion12.puzzle.repository.JoinClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static likelion12.puzzle.DTO.JoinClubDTO.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinClubService {
    private final JoinClubRepository joinClubRepository;
    private final MemberService memberService;
    private final ClubService clubService;

    // 동아리에 새로운 학생 추가
    @Transactional
    public JoinClub saveNewMember(String studentId, String studentName, String clubName) {
        Club club = clubService.findByName(clubName);
        Member member = new Member(studentId, studentName, club);
        JoinClub joinClub = new JoinClub(club, member);

        return joinClubRepository.saveNewMemberForClub(joinClub);
    }

    // 기존 학생, 기존 동아리
    @Transactional
    public JoinClub saveNewMember(String studentId, Club club) {
        Member member = memberService.findByStudentId(studentId);
        JoinClub joinClub = new JoinClub(club, member);

        return joinClubRepository.saveNewMemberForClub(joinClub);
    }

    // 동아리에 가입된 학생들 찾기
    public List<JoinClub> findAllByClubName(Club club) {
        return joinClubRepository.findAllByClubName(club);
    }

    // 학번으로 어느 동아리 가입되어있는지 조회
    public List<JoinClub> findByStudentId(String studentId) {
        return joinClubRepository.findByMemberId(studentId);
    }

    // 동아리에서 학생 탈퇴
    @Transactional
    public boolean deleteJoinClub(String student, String clubName) {
        Member member = memberService.findByStudentId(student);
        Club club = clubService.findByName(clubName);
        JoinClub joinClub = joinClubRepository.findJoinClub(club, member);

        return joinClubRepository.deleteJoinClub(joinClub);
    }

    // 동아리원 검색
    public List<CreateJC> searchByKeyword(String keyword) {
        return joinClubRepository.findCMManageByKeyword(keyword);
    }

    public List<Club> findByStudentIdClub(String studentId){
        return joinClubRepository.findByStudentIdClub(studentId);
    }

    // 모든 멤버 가입된 동아리 반환
    public List<MemberJoinedClubDTO> findJoinedClubsForAllMember(){
        return joinClubRepository.findJoinedClubsForAllMember();
    }

    // 특정 멤버의 가입동아리, 미가입 동아리 반환
    public MemberJoinedUnjoinedClubDTO findJoinedClubUnJoinedClub(String studentId){
        return joinClubRepository.findJoinedClubUnJoinedClub(studentId);
    }

    // 로그인을 위해서 member조회할 때 iconClub이 null이면 이 함수 사용할 것.
    @Transactional
    public void setRandomIconClub(String studentId){
        List<Club> joinedClubs = findByStudentIdClub(studentId);
        if (joinedClubs.isEmpty()){
            throw new NoJoinedClubException("가입된 club 없음.");
        } else{
            Member member = memberService.findByStudentId(studentId);
            member.updateIconClub(joinedClubs.get(0));
        }
    }
}
