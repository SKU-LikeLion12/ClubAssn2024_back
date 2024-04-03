package likelion12.puzzle.controller;

import likelion12.puzzle.DTO.JoinClubDTO;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.service.ClubService;
import likelion12.puzzle.service.ImageUtility;
import likelion12.puzzle.service.JoinClubService;
import likelion12.puzzle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static likelion12.puzzle.DTO.ClubDTO.*;


@RestController
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;
    private final MemberService memberService;
    private final JoinClubService joinClubService;

    @PostMapping("/club/add")
    public Club testClub(@RequestBody Club club) {
        return clubService.addNewClub(club.getName(), club.getDescription(), club.getLogo());
    }

    // 동아리 추가
    @PostMapping("/club") //, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Club> addClub(@RequestParam String clubName,
                                        @RequestParam String description,
                                        @RequestParam MultipartFile logo)  throws IOException {

        // 프론트에서 받은 이미지(이미지 그 자체) => getByte()함수로 byte배열로 변환
        byte[] imageBytes = logo.getBytes();
        Club club = clubService.addNewClub(clubName, description, imageBytes);
        return ResponseEntity.status(HttpStatus.CREATED).body(club);
    }

    // 동아리 수정
    @PutMapping("/club/{clubId}")
    public ResponseEntity<ClubCreateRequest> changeClub(@PathVariable Long clubId,
                                                        @RequestParam String clubName,
                                                        @RequestParam String description,
                                                        @RequestParam(required = false) MultipartFile logo) throws IOException {
        Club club = clubService.changeClub(clubId, clubName, description, logo);
        String base64Image = ImageUtility.encodeImage(club.getLogo());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClubCreateRequest(club.getName(), club.getDescription(), base64Image));
    }


    @GetMapping("club/{clubId}")
    public ClubCreateRequest findOneClub(@PathVariable("clubId") Long clubId) {
        Club club = clubService.findById(clubId);
        String base64Logo = ImageUtility.encodeImage(club.getLogo());
        return new ClubCreateRequest(club.getName(), club.getDescription(), base64Logo);
    }


    @GetMapping("/clubs")
    public List<ClubAllRequest> findAllClubs() {
        List<Club> clubs = clubService.findAll();
        List<ClubAllRequest> clubDTOS = new ArrayList<>();

        for (Club club : clubs) {
            ClubAllRequest dto = new ClubAllRequest(club.getId(), club.getName(), club.getDescription());
            clubDTOS.add(dto);
        }
        return clubDTOS;
    }
}

