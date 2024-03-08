package likelion12.puzzle.controller;

import likelion12.puzzle.DTO.ClubDTO.*;
import likelion12.puzzle.DTO.MemberDTO.*;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member/add")
    public ResponseMember addMember(@RequestBody RequestMember member) {
        Member member1 = memberService.addNewMember(member.getStudentId(), member.getName());

        return new ResponseMember(member1.getStudentId(), member1.getName());
    }
}