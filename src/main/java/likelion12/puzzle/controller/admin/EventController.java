package likelion12.puzzle.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import likelion12.puzzle.domain.Event;
import likelion12.puzzle.service.EventService;
import likelion12.puzzle.service.JoinEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static likelion12.puzzle.DTO.EventDTO.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/events")
public class EventController {
    private final EventService eventService;

    // 이벤트 추가
    @Operation(summary = "관리자가 퍼즐 조각 추가하는 API", description = "퍼즐 이름, 퍼즐 이미지, 퍼즐 행사 날짜 받아야함", tags={"Event", "add"},
            responses = {@ApiResponse(responseCode = "200", description = ""),
                    @ApiResponse(responseCode = "", description = "")})
    @PostMapping("/add")
    public ResponseEntity<ResponseEvent> addEvent(@RequestBody RequestEvent request) throws IOException {
        Event event = eventService.addEvent(request.getName(), request.getImage(), request.getDate());

        ResponseEvent responseEvent = new ResponseEvent(event.getId(), event.getName(), event.arrayToImage(), event.getDate());
        return ResponseEntity.ok(responseEvent);
    }

    // 퍼즐 조각 수정 페이지
//    @GetMapping("/events/manage/update")
//    public ResponseEntity<ResponseEvent> eventUpdatePage(@RequestBody RequestEvent request) {
//
//    }
//
    // 퍼즐 조각 수정 기능
//    @PostMapping("/events/manage/update")
//    public ResponseEntity<ResponseEvent> eventUpdatePage(@RequestBody RequestEvent request) {
//
//    }
}