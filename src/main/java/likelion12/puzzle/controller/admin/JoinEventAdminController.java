package likelion12.puzzle.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import likelion12.puzzle.DTO.JoinEventDTO.*;
import likelion12.puzzle.service.JoinEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static likelion12.puzzle.DTO.EventDTO.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/join-events")
public class JoinEventAdminController {
    public final JoinEventService joinEventService;

    // 회원 퍼즐 조각 관리 페이지
    @Operation(summary = "회원의 이벤트 리스트 조회", description = "관리자 토큰 필요, 바디에 학번 필요", tags = {"admin-join-event"})
    @PostMapping("")
    public ResponseEntity<List<ResponseJoinEvent>> manageEvents(@RequestBody RequestMember request) {
        List<ResponseJoinEvent> responsePuzzles = joinEventService.findAllJoinEvents(request.getStudentId());

        return ResponseEntity.ok(responsePuzzles);
    }

    // 회원 퍼즐 조각 삭제 페이지(기본키를 받아오나? 이벤트 이름만 받나?)
    @Operation(summary = "이벤트 참여 삭제", description = "관리자 토큰 필요, 바디에 학번 필요", tags = {"admin-join-event"})
    @DeleteMapping("")
    public void deleteEvents(@RequestBody RequestJoinEventForDelete request) {
        joinEventService.removeJoinEvent(request.getStudentId(), request.getId());
    }

    // 회원 퍼즐 조각 추가 페이지(참여 안한거만 나오게)
    @Operation(summary = "회원의 미참여 이벤트 리스트 조회", description = "관리자 토큰 필요, 바디에 학번 필요", tags = {"admin-join-event"})
    @GetMapping("")
    public ResponseEntity<List<ResponsePuzzleForNotPart>> pageForAddEvent(@RequestBody RequestMember request) {
        List<ResponsePuzzleForNotPart> responseEvents = joinEventService.findNotPartEventsExceptImage(request.getStudentId());

        return ResponseEntity.ok(responseEvents);
    }

    // 회원 퍼즐 조각 추가 +
    @Operation(summary = "이벤트 참여 추가", description = "관리자 토큰 필요, 바디에 학번 필요", tags = {"admin-join-event"})
    @PostMapping("/add")
    public ResponseEntity<?> addJoinEvent(@RequestBody RequestJoinEvent request) {
        joinEventService.saveJoinEvent(request.getStudentId(), request.getId());

        return ResponseEntity.ok().build();
    }
}
