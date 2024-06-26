package likelion12.puzzle.service;

import likelion12.puzzle.DTO.JoinClubDTO;
import likelion12.puzzle.DTO.MemberClubDTO.*;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.domain.JoinClub;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.exception.ExistJoinClubException;
import likelion12.puzzle.exception.NoJoinedClubException;
import likelion12.puzzle.exception.NotExistJoinClubException;
import likelion12.puzzle.repository.JoinClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    // 기존 학생, 기존 동아리
    @Transactional
    public JoinClub saveNewMember(String studentId, String clubName) {
        Member member = memberService.findByStudentId(studentId);
        Club club = clubService.findByName(clubName);
        if(findJoinClub(studentId, clubName)) {
            throw new ExistJoinClubException("이미 추가된 동아리원입니다.",HttpStatus.NOT_ACCEPTABLE);
        }
        JoinClub joinClub = new JoinClub(club, member);
        return joinClubRepository.saveNewMemberForClub(joinClub);
    }

    // 동아리에 가입된 학생들 찾기
    public List<JoinClub> findAllByClubName(String clubName) {
        Club club = clubService.findByName(clubName);
        return joinClubRepository.findAllByClubName(club);
    }

    // 학번으로 어느 동아리 가입되어있는지 조회
//    public List<JoinClub> findByStudentId(String studentId) {
//        return joinClubRepository.findByMemberId(studentId);
//    }

    // 학번으로 어느 동아리 가입되어있는지 조회
    public List<Club> findJoinedClubByMemberId(String studentId){
        return joinClubRepository.findJoinedClubByMemberId(studentId);
    }

    // 동아리에서 학생 탈퇴
    @Transactional
    public void deleteJoinClub(String studentId, String clubName) {
        Member member = memberService.findByStudentId(studentId);
        Club club = clubService.findByName(clubName);

        if (member.getIconClub()!=null && member.getIconClub().equals(club)) {
            member.setIconClub(null);
        }

        JoinClub joinClub = joinClubRepository.findJoinClub(club, member);

        joinClubRepository.deleteJoinClub(joinClub);
    }

    public boolean findJoinClub(String studentId, String clubName){
        Member member = memberService.findByStudentId(studentId);
        Club club = clubService.findByName(clubName);
        try{
            joinClubRepository.findJoinClub(club, member);
            return true;
        } catch (NotExistJoinClubException e) {
            return false;
        }
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


}
