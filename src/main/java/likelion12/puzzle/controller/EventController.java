package likelion12.puzzle.controller;

import io.swagger.v3.oas.annotations.Operation;
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
public class EventController {
    private final EventService eventService;

//    @GetMapping("/events")
//    public void myEvents() {
//        // 로그인 되어있으면 실행. 토큰 없으면 알림 후 로그인 페이지로 이동
//
//        // 로그인 되어있으면 member(이름, 로고, 동아리)
//        // joinevent 보고 member id 있는거에서 event id로 행사 id, name, image, date 뿌려주기
//    }

    // 퍼즐 조각 관리
//    @GetMapping("/events/manage")
//    public ResponseEntity<List<ResponseEvent>> eventsManage() {
//
//    }
    // 이벤트 추가
    @Operation(summary = "", description = "", tags={""})
    @PostMapping("/events/manage/add")
    public ResponseEntity<ResponseEvent> addEvent(@RequestBody RequestEvent request) throws IOException {
        Event event = eventService.addEvent(request.getName(), request.getImage(), request.getDate());

        ResponseEvent responseEvent = new ResponseEvent(event.getId(), event.getName(), event.arrayToImage(), event.getDate());
        return ResponseEntity.ok(responseEvent);
    }

//    // 퍼즐 조각 수정 페이지
//    @GetMapping("/events/manage/update")
//    public ResponseEntity<ResponseEvent> eventUpdatePage(@RequestBody RequestEvent request) {
//
//    }
//
//    // 퍼즐 조각 수정 기능
//    @PostMapping("/events/manage/update")
//    public ResponseEntity<ResponseEvent> eventUpdatePage(@RequestBody RequestEvent request) {
//
//    }
}