package likelion12.puzzle.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import likelion12.puzzle.DTO.ClubDTO;
import likelion12.puzzle.DTO.JoinClubDTO;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.service.ClubService;
import likelion12.puzzle.service.ImageUtility;
import likelion12.puzzle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static likelion12.puzzle.DTO.ClubDTO.ClubAllRequest;
import static likelion12.puzzle.DTO.ClubDTO.ClubCreateRequest;


@RestController
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;


//    @PostMapping("/club/add")
//    public Club testClub(@RequestBody Club club) {
//        return clubService.addNewClub(club.getName(), club.getDescription(), club.getLogo());
//    }

//    @PostMapping("/club/add/{studentId}")
//    public ResponseJoinClub addJoinClub(@RequestBody RequestJoinClub request, @PathVariable("studentId") String studentId) {
//        Club club = clubService.findByName(request.getClubName());
//        joinClubService.saveNewMember(studentId, club);
//
//        return new ResponseJoinClub(studentId, club.getName());
//    }

    // 동아리 추가
    @Operation(summary = "동아리 추가", description = "동아리명과 동아리 설명, 로고 사진 필요", tags = {"club", "add"},
            responses = {@ApiResponse(responseCode = "201", description = "생성 후 club 객체 반환"),
                    @ApiResponse(responseCode = "", description = "")})
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
    @Operation(summary = "동아리 수정", description = "동아리 id, 동아리명, 동아리 설명, 동아리 로고 필요", tags = {"club", "update"},
            responses = {@ApiResponse(responseCode = "201", description = "수정 성공 후 변경된 정보를 포함한 객체 생성 "),
                    @ApiResponse(responseCode = "", description = "")})
    @PutMapping("/club/{clubId}")
    public ResponseEntity<ClubCreateRequest> changeClub(@PathVariable Long clubId,
                                                        @RequestParam String clubName,
                                                        @RequestParam String description,
                                                        @RequestParam(required = false) MultipartFile logo) throws IOException {
        Club club = clubService.changeClub(clubId, clubName, description, logo);
        String base64Image = ImageUtility.encodeImage(club.getLogo());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClubCreateRequest(club.getName(), club.getDescription(), base64Image));
    }


    @Operation(summary = "", description = "", tags = {"club"},
            responses = {@ApiResponse(responseCode = "200", description = ""),
                    @ApiResponse(responseCode = "", description = "")})
    @GetMapping("club/{clubId}")
    public ResponseEntity<ClubCreateRequest> findOneClub(@PathVariable("clubId") Long clubId) {
        Club club = clubService.findById(clubId);
        String base64Logo = ImageUtility.encodeImage(club.getLogo());
        return ResponseEntity.status(HttpStatus.OK).body(new ClubCreateRequest(club.getName(), club.getDescription(), base64Logo));
    }


    @Operation(summary = "모든 동아리 조회", description = "모든 동아리에 대한 동아리 아이디, 동아리명, 동아리 설명 조회", tags = {"club", "get"},
            responses = {@ApiResponse(responseCode = "200", description = "조회를 하면 동아리의 아이디, 동아리명, 동아리 설명이 나타난다."),
                    @ApiResponse(responseCode = "", description = "")})
    @GetMapping("/clubs")
    public ResponseEntity<List<ClubAllRequest>> findAllClubs() {
        List<Club> clubs = clubService.findAll();
        List<ClubAllRequest> clubDTOS = new ArrayList<>();

        for (Club club : clubs) {
            ClubAllRequest dto = new ClubAllRequest(club.getId(), club.getName(), club.getDescription());
            clubDTOS.add(dto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(clubDTOS);
    }

    @Operation(summary = "동아리 삭제 API", description = "동아리 이름 넣으면 해당 동아리 삭제", tags={"club", "delete"},
            responses = {@ApiResponse(responseCode = "200", description = "동아리 삭제 성공.")})
    @DeleteMapping("/deleteClub")
    public boolean deleteClub(@RequestBody ClubDTO.RequestJoinClub request) {
        return clubService.deleteClub(request.getClubName());
    }
}

