package likelion12.puzzle.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion12.puzzle.domain.Event;
import likelion12.puzzle.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static likelion12.puzzle.DTO.EventDTO.RequestEvent;
import static likelion12.puzzle.DTO.EventDTO.ResponseEvent;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/events")
@Tag(name = "관리자 페이지: 퍼즐조각 관리 관련")
public class EventAdminController {
    private final EventService eventService;

    // 이벤트 추가
    @Operation(summary = "관리자가 새로운 퍼즐 조각 추가하는 API", description = "퍼즐 이름, 퍼즐 이미지, 퍼즐 행사 날짜 받아야함")
    @PostMapping("/add")
    public ResponseEntity<ResponseEvent> addEvent(@RequestBody RequestEvent request) throws IOException {
        Event event = eventService.addEvent(request.getName(), request.getImage(), request.getDate());

        ResponseEvent responseEvent = new ResponseEvent(event.getId(), event.getName(), event.arrayToImage(), event.getDate());
        return ResponseEntity.ok(responseEvent);
    }

    //이벤트 리스트 조회 추가 필요

    //개별 리스트 조회는 만들지 않을 예정

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
    // 퍼즐 조각 삭제
    @Operation(summary = "관리자가 퍼즐 조각 삭제하는 API", description = "퍼즐 id 입력")
    @DeleteMapping("/delete/{eventId}")
    public boolean eventDeletePage(@PathVariable("eventId") Long eventId){
        return eventService.removeEvent(eventId);
    }
}