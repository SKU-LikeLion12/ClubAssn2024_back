package likelion12.puzzle.controller;

import likelion12.puzzle.DTO.ClubDTO;
import likelion12.puzzle.DTO.JoinClubDTO;
import likelion12.puzzle.DTO.MemberClubDTO;
import likelion12.puzzle.DTO.MemberDTO;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.service.ClubService;
import likelion12.puzzle.service.JoinClubService;
import likelion12.puzzle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JoinClubController {
    private final ClubService clubService;
    private final JoinClubService joinClubService;


    @PostMapping("/club/add/{studentId}")
    public ClubDTO.ResponseJoinClub addJoinClub(@RequestBody ClubDTO.RequestJoinClub request, @PathVariable("studentId") String studentId) {
        Club club = clubService.findByName(request.getClubName());
        joinClubService.saveNewMember(studentId, club);

        return new ClubDTO.ResponseJoinClub(studentId, club.getName());
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

    // 모든 멤버의 가입된 클럽 리스트
    @PostMapping("/member/club-list")
    public ResponseEntity<List<MemberClubDTO.MemberJoinedClubDTO>> findJoinedClubsForAllMember(){
        return ResponseEntity.ok().body(joinClubService.findJoinedClubsForAllMember());
    }


    // 특정 멤버의 가입 동아리, 미가입 동아리 리스트
    @PostMapping("/member/club-info")
    public ResponseEntity<MemberClubDTO.MemberJoinedUnjoinedClubDTO> findJoinedClubUnJoinedClub(@RequestBody MemberDTO.RequestMember member){
        return ResponseEntity.ok().body(joinClubService.findJoinedClubUnJoinedClub(member.getStudentId()));
    }
}
