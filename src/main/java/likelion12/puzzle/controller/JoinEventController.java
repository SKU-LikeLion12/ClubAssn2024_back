package likelion12.puzzle.controller;

import likelion12.puzzle.DTO.EventDTO;
import likelion12.puzzle.service.JoinEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JoinEventController {
    public final JoinEventService joinEventService;

    // 회원 퍼즐 조각 관리 페이지
    @PostMapping("/events/manage/{studentId}")
    public ResponseEntity<List<EventDTO.ResponseJoinEvent>> manageEvents(@PathVariable("studentId") String studentId) {
        List<EventDTO.ResponseJoinEvent> responsePuzzles = joinEventService.findAllJoinEvents(studentId);

        return ResponseEntity.ok(responsePuzzles);
    }

    // 회원 퍼즐 조각 삭제 페이지(기본키를 받아오나? 이벤트 이름만 받나?)
    @DeleteMapping("/events/manage/{studentId}")
    public void deleteEvents(@PathVariable("studentId") String studentId, @RequestBody EventDTO.RequestJoinEventForDelete request) {
        joinEventService.removeJoinEvent(studentId, request.getId());
    }

    // 회원 퍼즐 조각 추가 페이지(참여 안한거만 나오게)
    @GetMapping("/events/manage/{studentId}/add")
    public ResponseEntity<List<EventDTO.ResponsePuzzleForNotPart>> pageForAddEvent(@PathVariable("studentId") String studentId) {
        List<EventDTO.ResponsePuzzleForNotPart> responseEvents = joinEventService.findNotPartEventsExceptImage(studentId);

        return ResponseEntity.ok(responseEvents);
    }

    // 회원 퍼즐 조각 추가 +
    @PostMapping("/events/manage/{studentId}/add")
    public ResponseEntity<?> addJoinEvent(@PathVariable("studentId") String studentId, @RequestBody EventDTO.RequestJoinEvent request) {
        joinEventService.saveJoinEvent(studentId, request.getId());

        return ResponseEntity.ok().build();
    }
}
