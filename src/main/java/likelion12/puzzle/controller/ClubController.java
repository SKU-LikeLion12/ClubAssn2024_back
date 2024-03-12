package likelion12.puzzle.controller;

import likelion12.puzzle.DTO.JoinClubDTO;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.service.ClubService;
import likelion12.puzzle.service.ImageUtility;
import likelion12.puzzle.service.JoinClubService;
import likelion12.puzzle.service.JoinEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static likelion12.puzzle.DTO.ClubDTO.*;

@RestController
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;
    private final JoinClubService joinClubService;

    // 동아리원 검색
    @GetMapping("/member/manage")
    public ResponseEntity<List<JoinClubDTO>> CMManageSearch(@RequestParam String keyword) {
//        if (keyword == null || keyword.trim().isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
        List<JoinClubDTO> results = joinClubService.searchByKeyword(keyword);
        if(results.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(results);
        }
    }
}
