package likelion12.puzzle.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "멤버 페이지: 가입된 동아리 관련")
public class JoinClubController {
    private final JoinClubService joinClubService;
    private final MemberService memberService;
    private final JwtUtility jwtUtility;

    // 아이콘을 포함해서 가입되어있는 모든 조인클럽 반환.
    @GetMapping("/joined-list")
    @Operation(summary = "(택원) 멤버가 가입되어있는 모든 동아리 조회", description = "발급된 jwt 필요",
            responses = {@ApiResponse(responseCode="200", description="정상 로그인"),
                    @ApiResponse(responseCode = "403", description = "권한 없음")
            })
    public List<Club> findJoinedClubByMemberId(HttpServletRequest header){
        String studentId = jwtUtility.getStudentId(header.getHeader("Authorization"));
        return joinClubService.findJoinedClubByMemberId(studentId);
    }

    // iconclub 변경
    @PostMapping("/changeIconClub/{clubName}")
    @Operation(summary = "(택원) 대표 동아리 변경", description = "jwt, 동아리명 필요",
            responses = {@ApiResponse(responseCode="200", description="변경 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음")
            })
    public void changeIconClub(HttpServletRequest header, @PathVariable("clubName") String clubName){
        String studentId = jwtUtility.getStudentId(header.getHeader("Authorization"));
        memberService.changeIconClub(studentId, clubName);
    }
}
