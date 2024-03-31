package likelion12.puzzle.controller;

import likelion12.puzzle.DTO.MemberDTO.MemberToken;
import likelion12.puzzle.security.JwtUtility;
import likelion12.puzzle.service.ItemRentService;
import likelion12.puzzle.service.JoinClubService;
import likelion12.puzzle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import likelion12.puzzle.DTO.MemberDTO.*;
import likelion12.puzzle.domain.Member;

import java.util.List;
import java.util.Map;

import static likelion12.puzzle.DTO.ItemRentDTO.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtUtility jwtUtility;
    private final ItemRentService itemRentService;
    private final JoinClubService joinClubService;

    @GetMapping("/member/book-list")
    public ResponseEntity<List<BookDTO>> memberBookList(MemberToken token){
        List<BookDTO> list = itemRentService.memberBookList(jwtUtility.getStudentId(token));
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/member/rent-list")
    public ResponseEntity<List<RentDTO>> memberRentList(MemberToken token){
        List<RentDTO> list = itemRentService.memberRentList(jwtUtility.getStudentId(token));
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/member/add")
    public ResponseMember addMember(@RequestBody RequestMember member) {
        Member member1 = memberService.addNewMember(member.getStudentId(), member.getName());

        return new ResponseMember(member1.getStudentId(), member1.getName());
    }

    @PostMapping("/member/club")
    public ResponseEntity<Map<String, Object>> findMyClub(MemberToken token){

        return null;
    }
}