package likelion12.puzzle.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import likelion12.puzzle.DTO.MemberDTO.*;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.DTO.MemberClubDTO.*;
import likelion12.puzzle.security.JwtUtility;
import likelion12.puzzle.service.ItemRentService;
import likelion12.puzzle.service.JoinClubService;
import likelion12.puzzle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static likelion12.puzzle.DTO.ItemRentDTO.BookDTO;
import static likelion12.puzzle.DTO.ItemRentDTO.RentDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final JwtUtility jwtUtility;
    private final ItemRentService itemRentService;
    private final JoinClubService joinClubService;

    @Operation(summary = "로그인", description = "학번과 이름 필요", tags={"login"})
    @GetMapping("/login")
    public ResponseEntity<ResponseLogin> login(@RequestBody RequestMember request) {
        return ResponseEntity.ok(memberService.login(request));
    }

    @Operation(summary = "특정 멤버가 예약중인 물품 리스트", description = "헤더에 토큰 필요", tags={"myPage"})
    @GetMapping("/book-list")
    public ResponseEntity<List<BookDTO>> memberBookList(HttpServletRequest header){
        List<BookDTO> list = itemRentService.memberBookList(jwtUtility.getStudentId(header.getHeader("Authorization")));
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "특정 멤버가 대여중인 물품 리스트", description = "헤더에 토큰 필요", tags={"myPage"})
    @GetMapping("/rent-list")
    public ResponseEntity<List<RentDTO>> memberRentList(HttpServletRequest header){
        List<RentDTO> list = itemRentService.memberRentList(jwtUtility.getStudentId(header.getHeader("Authorization")));
        return ResponseEntity.ok().body(list);
    }

    // 테스트용
//    @PostMapping("/add")
//    public ResponseMember addMember(@RequestBody RequestMember request) {
//        Member member = memberService.addNewMember(request.getStudentId(), request.getName());
//
//        return new ResponseMember(member.getStudentId(), member.getName(), "asdf");
//    }
}