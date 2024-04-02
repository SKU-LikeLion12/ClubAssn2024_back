package likelion12.puzzle.service;

import likelion12.puzzle.DTO.MemberClubDTO;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.exception.DuplicatedStudentIdException;
import likelion12.puzzle.exception.NoJoinedClubException;
import likelion12.puzzle.repository.JoinClubRepository;
import likelion12.puzzle.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final JoinClubRepository joinClubRepository;
    private final JoinClubService joinClubService;

    // 첫 화면 로그인
    public Member login(String studentId, String name) {
        Member member = findByStudentId(studentId);

        if (member.getName().equals(name)) {
            return member;
        } else {
            return null;
        }
    }

    // 새로운 학생 추가
    @Transactional
    public Member addNewMember(String studentId, String name, Club iconClub) {
        Member member = new Member(studentId, name, iconClub);
        memberRepository.addNewMember(member);

        return member;
    }

    // 테스트용
    @Transactional
    public Member addNewMember(String studentId, String name) {
        if (isDuplicated(studentId)){
            throw new DuplicatedStudentIdException(studentId + "번은 이미 가입된 학번입니다.");
        }
        Member member = new Member(studentId, name);
        memberRepository.addNewMember(member);

        return member;
    }

    public boolean isDuplicated(String studentId){
        Member member = memberRepository.findByStudentId(studentId);
        return member == null;
    }

    // 대표 동아리 변경
    public Member updateIconClub(Member member, Club newIconClub) {
        member.updateIconClub(newIconClub);

        return member;
    }

    // 학번으로 조회
    public Member findByStudentId(String studentId) {
        return memberRepository.findByStudentId(studentId);
    }

    // 이름으로 조회
    public List<Member> findByName(String name) {
        return memberRepository.findByName(name);
    }

    // 전체 조회
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    // 학생 삭제
    @Transactional
    public boolean deleteMember(String studentId) {
        Member member = findByStudentId(studentId);

        return memberRepository.deleteMember(member);
    }

    // 로그인을 위해서 member조회할 때 iconClub이 null이면 이 함수 사용할 것.
    @Transactional
    public void setRandomIconClub(String studentId){
        List<Club> joinedClubs = joinClubService.findByStudentIdClub(studentId);
        if (joinedClubs.isEmpty()){
            throw new NoJoinedClubException("가입된 club 없음.");
        }
    }
}