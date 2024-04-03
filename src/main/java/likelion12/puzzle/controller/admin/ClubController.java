package likelion12.puzzle.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import likelion12.puzzle.DTO.ClubDTO.ClubUpdateRequest;
import likelion12.puzzle.DTO.ClubDTO.RequestJoinClub;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static likelion12.puzzle.DTO.ClubDTO.ClubAllRequest;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/club")
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
    @Operation(summary = "동아리 추가", description = "동아리명과 동아리 설명, 로고 사진 필요", tags = {"admin-club"},
            responses = {@ApiResponse(responseCode = "200", description = "생성"),
                    @ApiResponse(responseCode = "", description = "")})
    @PostMapping("/add") //, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Club> addClub(@RequestParam String clubName,
                                        @RequestParam String description,
                                        @RequestParam(required = false) MultipartFile logo)  throws IOException {
        Club club = clubService.addNewClub(clubName, description, logo);
        return ResponseEntity.status(HttpStatus.CREATED).body(club);
    }

    // 동아리 수정
    @Operation(summary = "동아리 수정", description = "동아리 id, 동아리명, 동아리 설명 필요, 이미지는 안바꾸고 싶으면 안넣으면 됨", tags = {"admin-club"},
            responses = {@ApiResponse(responseCode = "201", description = "수정 성공 후 변경된 정보를 포함한 객체 생성 "),
                    @ApiResponse(responseCode = "", description = "")})
    @PutMapping("/{clubId}")
    public ResponseEntity<ClubUpdateRequest> changeClub(@RequestParam String clubName,
                                                        @RequestParam String description,
                                                        @RequestParam(required = false) MultipartFile logo) throws IOException {
        Club club = clubService.changeClub(clubName, description, logo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClubUpdateRequest(club.getName(), club.getDescription(), club.arrayToImage()));
    }

//    @Operation(summary = "", description = "", tags = {"club"},
//            responses = {@ApiResponse(responseCode = "200", description = ""),
//                    @ApiResponse(responseCode = "", description = "")})
//    @GetMapping("club/{clubId}")
//    public ResponseEntity<ClubCreateRequest> findOneClub(@PathVariable("clubId") Long clubId) {
//        Club club = clubService.findById(clubId);
//        return ResponseEntity.status(HttpStatus.OK).body(new ClubCreateRequest(club.getName(), club.getDescription(), club.arrayToImage()));
//    }


    @Operation(summary = "모든 동아리 조회", description = "모든 동아리에 대한 동아리 아이디, 동아리명, 동아리 설명 조회", tags = {"admin-club"},
            responses = {@ApiResponse(responseCode = "200", description = "조회를 하면 동아리의 아이디, 동아리명, 동아리 설명이 나타난다."),
                    @ApiResponse(responseCode = "", description = "")})
    @GetMapping("/all")
    public ResponseEntity<List<ClubAllRequest>> findAllClubs() {
        List<Club> clubs = clubService.findAll();
        List<ClubAllRequest> clubDTOS = new ArrayList<>();

        for (Club club : clubs) {
            ClubAllRequest dto = new ClubAllRequest(club.getName(), club.getDescription());
            clubDTOS.add(dto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(clubDTOS);
    }

    @Operation(summary = "동아리 삭제 API", description = "동아리 이름 넣으면 해당 동아리 삭제", tags={"admin-club"},
            responses = {@ApiResponse(responseCode = "200", description = "동아리 삭제 성공.")})
    @DeleteMapping("")
    public boolean deleteClub(@RequestBody RequestJoinClub request) {
        return clubService.deleteClub(request.getClubName());
    }
}