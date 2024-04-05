package likelion12.puzzle.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion12.puzzle.DTO.ClubDTO.*;
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
@Tag(name = "관리자 페이지: 동아리 관리 관련")
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
    @Operation(summary = "(민규) 동아리 추가", description = "동아리명과 동아리 설명, 로고 사진 필요")
    @PostMapping("/add") //, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseClub> addClub(ClubCreateRequest request)  throws IOException {
        Club club = clubService.addNewClub(request.getName(), request.getDescription(), request.getLogo());
        ResponseClub responseClub = new ResponseClub(club.getId(), club.getName(), club.getDescription(), club.arrayToImage());
        return ResponseEntity.ok(responseClub);
    }

    // 동아리 수정
    @Operation(summary = "(민규) 동아리 수정", description = "동아리 id, 동아리명, 동아리 설명 필요, 이미지는 안바꾸고 싶으면 안넣으면 됨")
    @PutMapping("/update")
    public ResponseEntity<ClubUpdateResponse> changeClub(ClubUpdateRequest request) throws IOException {
        Club club = clubService.changeClub(request.getId(), request.getName(), request.getDescription(), request.getLogo());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClubUpdateResponse(club.getName(), club.getDescription(), club.arrayToImage()));
    }

//    @Operation(summary = "", description = "", tags = {"club"},
//            responses = {@ApiResponse(responseCode = "200", description = ""),
//                    @ApiResponse(responseCode = "", description = "")})
//    @GetMapping("club/{clubId}")
//    public ResponseEntity<ClubCreateRequest> findOneClub(@PathVariable("clubId") Long clubId) {
//        Club club = clubService.findById(clubId);
//        return ResponseEntity.status(HttpStatus.OK).body(new ClubCreateRequest(club.getName(), club.getDescription(), club.arrayToImage()));
//    }


    @Operation(summary = "(민규) 모든 동아리 조회", description = "모든 동아리에 대한 동아리 아이디, 동아리명, 동아리 설명 조회")
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

    @Operation(summary = "동아리 삭제", description = "동아리 이름 넣으면 해당 동아리 삭제")
    @DeleteMapping("")
    public boolean deleteClub(@RequestBody RequestJoinClub request) {
        return clubService.deleteClub(request.getClubName());
    }
}