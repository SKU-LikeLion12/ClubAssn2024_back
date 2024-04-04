package likelion12.puzzle.service;

import jakarta.servlet.http.HttpServletRequest;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.exception.DuplicatedStudentIdException;
import likelion12.puzzle.DTO.MemberDTO.*;
import likelion12.puzzle.domain.RoleType;
import likelion12.puzzle.exception.MemberExistException;
import likelion12.puzzle.exception.MemberLoginException;
import likelion12.puzzle.repository.MemberRepository;
import likelion12.puzzle.security.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final ClubService clubService;
    private final MemberRepository memberRepository;
    private final JwtUtility jwtUtility;

    // 토큰으로 멤버 추출
    public Member tokenToMember(HttpServletRequest request) {
        return memberRepository.findByStudentId(jwtUtility.getStudentId(jwtUtility.resolveToken(request)));
    }

    // 로그인
    public ResponseLogin login(RequestMember request) {
        Member member = memberRepository.findByStudentId(request.getStudentId());
        System.out.println("request = " + request);

        if (!Objects.equals(member.getName(), request.getName())) {
            throw new MemberLoginException("동아리원만 이용 가능합니다.\n학번과 이름을 확인해주세요.", HttpStatus.BAD_REQUEST);
        }

        if (!member.isAgree()) {
            throw new MemberLoginException("개인정보 동의가 필요합니다.", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseLogin(jwtUtility.generateToken(member.getStudentId()));
        }
    }

    // 동의 체크
    @Transactional
    public void updateAgree(RequestMember request) {
        Member member = memberRepository.findByStudentId(request.getStudentId());
        member.updateIsAgree();
    }

    // 새로운 학생 추가
    @Transactional
    public Member addNewMember(String studentId, String name, RoleType role) {
//        Club iconClub = clubService.findByName(clubName);
        Member alreadyExistsMember = memberRepository.findByStudentId(studentId);
        if (alreadyExistsMember == null) {
            Member member = new Member(studentId, name, role);
            memberRepository.addNewMember(member);

            return member;
        }
        throw new MemberExistException("이미 존재하는 학번입니다.", HttpStatus.BAD_REQUEST);
    }

    // 테스트용
//    @Transactional
//    public Member addNewMember(String studentId, String name) {
//        if (isDuplicated(studentId)){
//            throw new DuplicatedStudentIdException(studentId + "번은 이미 가입된 학번입니다.");
//        }
//        Member member = new Member(studentId, name);
//        memberRepository.addNewMember(member);
//
//        return member;
//    }


    public boolean isDuplicated(String studentId){
        Member member = memberRepository.findByStudentId(studentId);
        return member == null;
    }

    // 대표 동아리 변경
    public Member updateIconClub(String studentId, String clubName) {
        Member member = memberRepository.findByStudentId(studentId);
        Club newIconClub = clubService.findByName(clubName);
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

    @Transactional
    public void changeIconClub(String studentId, String clubName){
        Club club = clubService.findByName(clubName);
        Member member = memberRepository.findByStudentId(studentId);
        member.setIconClub(club);
    }
}