package likelion12.puzzle.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import likelion12.puzzle.domain.JoinClub;
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
public class JoinClubController {

    private final JoinClubService joinClubService;

    @Operation(summary = "동아리원 추가", description = "학번, 성함, 동아리명 필요", tags = {"clubMember-manage"},
            responses = {@ApiResponse(responseCode = "201", description = "생성 성공 후 joinClub 객체 반환"),
                    @ApiResponse(responseCode = "", description = "")})
    @PostMapping("/member/manage/add")
    public ResponseEntity<JoinClub> addNewMember(@RequestBody CreateJC request) {
        JoinClub joinClub = joinClubService.saveNewMember(request.getStudentId(), request.getStudentName(), request.getClubName());
        return ResponseEntity.status(HttpStatus.CREATED).body(joinClub);
//        try {
//            JoinClub joinClub = joinClubService.saveNewMember(request.getStudentId(), request.getStudentName(), request.getClubName());
//            return ResponseEntity.status(HttpStatus.CREATED).body(joinClub);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
    }

    @Operation(summary = "동아리원 삭제", description = "학번, 동아리 이름 필요", tags = {"clubMember-manage"},
            responses = {@ApiResponse(responseCode = "204", description = "삭제 성공 후 삭제 완료 메시지 반환"),
                    @ApiResponse(responseCode = "", description = "")})
    @DeleteMapping("/member/manage")
    public ResponseEntity<String> deleteClubMember(@RequestBody DeleteJC request) {
        joinClubService.deleteJoinClub(request.getStudentId(), request.getClubName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("삭제 완료");
    }

    @Operation(summary = "동아리원 검색", description = "학번, 이름, 동아리를 검색하면 알맞은 동아리원 정보가 나옴", tags = {"clubMember-manage"},
            responses = {@ApiResponse(responseCode = "200", description = "검색 성공 후 학번, 성함, 동아리명 반환"),
                    @ApiResponse(responseCode = "204", description = "없는 정보를 입력하면 요청에 대해 보내줄 콘텐츠가 없음")})
    @GetMapping("/member/manage")
    public ResponseEntity<List<CreateJC>> CMManageSearch(@RequestParam String keyword) {
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
}
