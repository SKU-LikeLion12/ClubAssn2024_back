package likelion12.puzzle.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.security.JwtUtility;
import likelion12.puzzle.service.JoinClubService;
import likelion12.puzzle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JoinClubController {
    private final JoinClubService joinClubService;
    private final MemberService memberService;
    private final JwtUtility jwtUtility;

    // 아이콘을 포함해서 가입되어있는 모든 조인클럽 반환.
    @GetMapping("/joined-list")
    public List<Club> findJoinedClubByMemberId(HttpServletRequest header){
        String studentId = jwtUtility.getStudentId(header.getHeader("Authorization"));
        return joinClubService.findJoinedClubByMemberId(studentId);
    }

    // iconclub 변경
    @PostMapping("/changeIconClub/{clubName}")
    public void changeIconClub(HttpServletRequest header, @PathVariable("clubName") String clubName){
        String studentId = jwtUtility.getStudentId(header.getHeader("Authorization"));
        memberService.changeIconClub(studentId, clubName);
    }

}
