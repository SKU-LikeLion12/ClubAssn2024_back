package likelion12.puzzle.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion12.puzzle.DTO.JoinEventDTO;
import likelion12.puzzle.DTO.MemberClubDTO;
import likelion12.puzzle.service.ClubService;
import likelion12.puzzle.service.JoinClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static likelion12.puzzle.DTO.JoinClubDTO.CreateJC;
import static likelion12.puzzle.DTO.JoinClubDTO.DeleteJC;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/join-club")
@Tag(name = "관리자 페이지: 동아리 관리 관련")
public class JoinClubAdminController {

    private final JoinClubService joinClubService;
    private final ClubService clubService;

    @Operation(summary = "동아리원 추가", description = "학번, 성함, 동아리명 필요",
            responses = {@ApiResponse(responseCode = "201", description = "생성 성공 후 joinClub 객체 반환"),
                    @ApiResponse(responseCode = "", description = "")})
    @PostMapping("/add")
    public ResponseEntity<?> addNewMember(@RequestBody CreateJC request) {
        joinClubService.saveNewMember(request.getStudentId(), request.getClubName());

        return ResponseEntity.status(HttpStatus.CREATED).build();
//        try {
//            JoinClub joinClub = joinClubService.saveNewMember(request.getStudentId(), request.getStudentName(), request.getClubName());
//            return ResponseEntity.status(HttpStatus.CREATED).body(joinClub);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
    }

    @Operation(summary = "동아리원 삭제", description = "학번, 동아리 이름 필요",
            responses = {@ApiResponse(responseCode = "204", description = "삭제 성공 후 삭제 완료 메시지 반환"),
                    @ApiResponse(responseCode = "", description = "")})
    @DeleteMapping("")
    public ResponseEntity<String> deleteClubMember(@RequestBody DeleteJC request) {
        joinClubService.deleteJoinClub(request.getMemberId(), request.getClubName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("삭제 완료");
    }

    @Operation(summary = "동아리원 검색", description = "학번, 이름, 동아리를 검색하면 알맞은 동아리원 정보가 나옴",
            responses = {@ApiResponse(responseCode = "200", description = "검색 성공 후 학번, 성함, 동아리명 반환"),
                    @ApiResponse(responseCode = "204", description = "없는 정보를 입력하면 요청에 대해 보내줄 콘텐츠가 없음")})
    @GetMapping("/{keyword}")
    public ResponseEntity<List<CreateJC>> CMManageSearch(@PathVariable String keyword) {
//        if (keyword == null || keyword.trim().isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
        List<CreateJC> results = joinClubService.searchByKeyword(keyword);
        if(results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(results);
        }
    }
//    @Operation(summary = "학생에게 동아리 부여(동아리 가입) API", description = "body에 동아리명, url에 학번 입력", tags={"joinclub", "add"})
//    @PostMapping("/club/add/{studentId}")
//    public ClubDTO.ResponseJoinClub addJoinClub(@RequestBody ClubDTO.RequestJoinClub request, @PathVariable("studentId") String studentId) {
//        joinClubService.saveNewMember(studentId, request.getClubName());
//        return new ClubDTO.ResponseJoinClub(studentId, request.getClubName());
//    }


    // 모든 멤버의 가입된 클럽 리스트
    @Operation(summary = "모든 멤버의 가입된 동아리 리스트 반환 API", description = "")
    @GetMapping("/all-list")
    public ResponseEntity<List<MemberClubDTO.MemberJoinedClubDTO>> findJoinedClubsForAllMember(){
        return ResponseEntity.ok().body(joinClubService.findJoinedClubsForAllMember());
    }


    // 특정 멤버의 가입 동아리, 미가입 동아리 리스트
    @Operation(summary = "학생이 가입한 동아리와 가입하지 않은 동아리 반환 API", description = "url에 학번 입력")
    @GetMapping("/info/{studentId}")
    public ResponseEntity<MemberClubDTO.MemberJoinedUnjoinedClubDTO> findJoinedClubUnJoinedClub(@RequestParam("studentId") String studentId){
        return ResponseEntity.ok().body(joinClubService.findJoinedClubUnJoinedClub(studentId));
    }
}
