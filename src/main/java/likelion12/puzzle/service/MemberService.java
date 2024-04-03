package likelion12.puzzle.service;

import likelion12.puzzle.DTO.MemberDTO.*;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.domain.RoleType;
import likelion12.puzzle.exception.MemberLoginException;
import likelion12.puzzle.repository.MemberRepository;
import likelion12.puzzle.security.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtility jwtUtility;

    // 첫 화면 로그인
//    public Member login(String studentId, String name) {
//        Member member = findByStudentId(studentId);
//
//        if (member.getName().equals(name)) {
//            return member;
//        } else {
//            return null;
//        }
//    }

    // 로그인
    public ResponseLogin login(RequestMember request) {
        Member member = memberRepository.findByStudentId(request.getStudentId());

        if (member == null) {
            throw new MemberLoginException("없는 학번입니다.", HttpStatus.BAD_REQUEST);
        }

        if (!Objects.equals(member.getName(), request.getName())) {
            throw new MemberLoginException("이름이 틀렸습니다.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseLogin(jwtUtility.generateToken(member.getStudentId()));
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
        Member member = new Member(studentId, name);
        memberRepository.addNewMember(member);

        return member;
    }

    @Transactional
    public Member addNewMember(String studentId, String name, RoleType role) {
        Member member = new Member(studentId, name, role);
        System.out.println("member.getName() = " + member.getName());
        memberRepository.addNewMember(member);

        return member;
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
}