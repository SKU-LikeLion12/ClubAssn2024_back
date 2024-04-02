package likelion12.puzzle.controller;

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

    @DeleteMapping("/member/manage")
    public ResponseEntity<String> deleteClubMember(@RequestBody DeleteJC request) {
        joinClubService.deleteJoinClub(request.getStudentId(), request.getClubName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("삭제 완료");
    }

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
