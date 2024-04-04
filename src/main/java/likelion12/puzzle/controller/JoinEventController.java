package likelion12.puzzle.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import likelion12.puzzle.DTO.EventDTO.AllEvents;
import likelion12.puzzle.security.JwtUtility;
import likelion12.puzzle.service.JoinEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "멤버 페이지: 퍼즐조각 관련")
public class JoinEventController {
    private final JoinEventService joinEventService;
    private final JwtUtility jwtUtility;

    //멤버가 자신의 이벤트 조회(해당 멤버가 참여 했는지 여부를 포함한 모든 이벤트 조회)
    @PostMapping("/puzzle")
    public ResponseEntity<List<AllEvents>> findEventsInfo(HttpServletRequest header){
        String studentId = jwtUtility.getStudentId(header.getHeader("Authorization"));
        return ResponseEntity.ok().body(joinEventService.findEventsInfo(studentId));
    }
}
