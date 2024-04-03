package likelion12.puzzle.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import likelion12.puzzle.DTO.MemberDTO.RequestMember;
import likelion12.puzzle.DTO.MemberDTO.ResponseMember;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.DTO.MemberClubDTO.*;
import likelion12.puzzle.security.JwtUtility;
import likelion12.puzzle.service.ItemRentService;
import likelion12.puzzle.service.JoinClubService;
import likelion12.puzzle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static likelion12.puzzle.DTO.ItemRentDTO.BookDTO;
import static likelion12.puzzle.DTO.ItemRentDTO.RentDTO;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtUtility jwtUtility;
    private final ItemRentService itemRentService;
    private final JoinClubService joinClubService;

    @Operation(summary = "특정 멤버가 예약중인 물품 리스트", description = "헤더에 토큰 필요", tags={"myPage"})
    @GetMapping("/member/book-list")
    public ResponseEntity<List<BookDTO>> memberBookList(HttpServletRequest header){
        List<BookDTO> list = itemRentService.memberBookList(jwtUtility.getStudentId(header.getHeader("Authorization")));
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "특정 멤버가 대여중인 물품 리스트", description = "헤더에 토큰 필요", tags={"myPage"})
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

    // 모든 멤버의 가입된 클럽 리스트
    @PostMapping("/member/club-list")
    public ResponseEntity<List<MemberJoinedClubDTO>> findJoinedClubsForAllMember(){
        return ResponseEntity.ok().body(joinClubService.findJoinedClubsForAllMember());
    }

    // 특정 멤버의 가입 동아리, 미가입 동아리 리스트
    @PostMapping("/member/club-info")
    public ResponseEntity<MemberJoinedUnjoinedClubDTO> findJoinedClubUnJoinedClub(@RequestBody RequestMember member){
        return ResponseEntity.ok().body(joinClubService.findJoinedClubUnJoinedClub(member.getStudentId()));
    }
}