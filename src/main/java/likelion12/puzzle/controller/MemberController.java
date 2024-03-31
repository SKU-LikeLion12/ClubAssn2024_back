package likelion12.puzzle.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion12.puzzle.security.JwtUtility;
import likelion12.puzzle.service.ItemRentService;
import likelion12.puzzle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import likelion12.puzzle.DTO.MemberDTO.*;
import likelion12.puzzle.domain.Member;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static likelion12.puzzle.DTO.ItemRentDTO.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtUtility jwtUtility;
    private final ItemRentService itemRentService;

    @GetMapping("/member/book-list")
    public ResponseEntity<List<BookDTO>> memberBookList(HttpServletRequest header){
        List<BookDTO> list = itemRentService.memberBookList(jwtUtility.getStudentId(header.getHeader("Authorization")));
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/member/rent-list")
    public ResponseEntity<List<RentDTO>> memberRentList(HttpServletRequest header){
        List<RentDTO> list = itemRentService.memberRentList(jwtUtility.getStudentId(header.getHeader("Authorization")));
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/member/add")
    public ResponseMember addMember(@RequestBody RequestMember member) {
        Member member1 = memberService.addNewMember(member.getStudentId(), member.getName());

        return new ResponseMember(member1.getStudentId(), member1.getName());
    }
}