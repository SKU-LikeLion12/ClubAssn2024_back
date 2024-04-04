package likelion12.puzzle.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion12.puzzle.DTO.MemberDTO.AddMemberResponse;
import likelion12.puzzle.DTO.MemberDTO.AddRequestMember;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name = "관리자 페이지: 학생 관리 관련")
public class MemberAdminController {
    private final MemberService memberService;

    @Operation(summary = "(호주) 새로운 학생 추가", description = "request: 학번, 성함, 권한<br>response: 학번, 성함, 권한",
            responses = {@ApiResponse(responseCode = "201", description = "생성 성공 후 joinClub 객체 반환"),
                        @ApiResponse(responseCode = "", description = "")})
    @PostMapping("/member/add")
    public AddMemberResponse addMember(@RequestBody AddRequestMember request) {
        Member member = memberService.addNewMember(request.getStudentId(), request.getName(), request.getRole());

        return new AddMemberResponse(member.getStudentId(), member.getName(), member.getRole());
    }
}