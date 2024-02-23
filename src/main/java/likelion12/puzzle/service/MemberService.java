package likelion12.puzzle.service;

import likelion12.puzzle.domain.Member;
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

    // 첫 화면 로그인
    public Member login(int studentId, String name) {
        Member member = memberRepository.findByStudentId(studentId);
        if (member.getName() == name) {
            return member;
        } else {
            return null;
        }
    }

    // 학번으로 조회
    public Member findByStudentId(int studentId) {
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
    public boolean deleteMember(int studentId) {
        memberRepository.deleteMember(studentId);
        return true;
    }
}