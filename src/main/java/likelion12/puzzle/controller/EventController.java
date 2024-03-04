package likelion12.puzzle.controller;

import likelion12.puzzle.domain.Event;
import likelion12.puzzle.domain.JoinEvent;
import likelion12.puzzle.service.EventService;
import likelion12.puzzle.service.JoinEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static likelion12.puzzle.DTO.EventDTO.*;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final JoinEventService joinEventService;

    @GetMapping("/events")
    public void myEvents() {
        // 로그인 되어있으면 실행. 토큰 없으면 알림 후 로그인 페이지로 이동

        // 로그인 되어있으면 member(이름, 로고, 동아리)
        // joinevent 보고 member id 있는거에서 event id로 행사 id, name, image, date 뿌려주기
    }

    // 퍼즐 조각 관리 페이지
    @PostMapping("/events/manage/{studentId}")
    public ResponseEntity<List<ResponsePuzzle>> manageEvents(@PathVariable("studentId") int studentId) {
        List<JoinEvent> joinEvents = joinEventService.findAllJoinEvents(studentId);

        List<ResponsePuzzle> responsePuzzles = joinEvents.stream()
                .map(ResponsePuzzle::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responsePuzzles);
    }

    // 퍼즐 조각 삭제 페이지
    @DeleteMapping("/events/manage/{studentId}")
    public void deleteEvents(@PathVariable("studentId") int studentId, @RequestBody RequestEvent request) {
        joinEventService.removeJoinEvent(studentId, request.getId());
    }

    // 퍼즐 조각 추가 페이지
    @PutMapping("/events/manage/add")
    public ResponseEntity<List<ResponseEvent>> pageForEventAdd(@PathVariable("studentId") int studentId) {
        List<Event> events = eventService.findAllEvents();

        List<ResponseEvent> responseEvents = events.stream()
                .map(ResponseEvent::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseEvents);
    }

    // 퍼즐 조각 추가(예외 처리 필요)
    @PostMapping("/events/manage/add/{studentId}")
    public ResponseEntity<ResponsePuzzle> addEvents(@PathVariable("studentId") int studentId, @RequestBody RequestEvent request) {
        JoinEvent joinEvent = joinEventService.saveJoinEvent(studentId, request.getId());
        return ResponseEntity.ok(new ResponsePuzzle(joinEvent));
    }
}