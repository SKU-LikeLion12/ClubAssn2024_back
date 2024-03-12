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

    // 동아리 추가
    @ResponseBody
    @PostMapping("/club") //, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Club> addClub(@RequestParam String clubName,
                                        @RequestParam String description,
                                        @RequestParam MultipartFile logo)  throws IOException {

        // 프론트에서 받은 이미지(이미지 그 자체) => getByte()함수로 byte배열로 변환
        byte[] imageBytes = logo.getBytes();
        Club club = clubService.addNewClub(clubName, description, imageBytes);
        return ResponseEntity.status(HttpStatus.CREATED).body(club);
    }

    @PutMapping("/club/{clubId}")
    public ResponseEntity<ClubCreateRequest> changeClub(@PathVariable Long clubId,
                                                        @RequestParam String clubName,
                                                        @RequestParam String description,
                                                        @RequestParam(required = false) MultipartFile logo) throws IOException {
        Club club = clubService.changeClub(clubId, clubName, description, logo);
        String base64Image = ImageUtility.encodeImage(club.getLogo());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClubCreateRequest(club.getName(), club.getDescription(), base64Image));
    }

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
