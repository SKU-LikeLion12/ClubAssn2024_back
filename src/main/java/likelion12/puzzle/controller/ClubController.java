package likelion12.puzzle.controller;

import likelion12.puzzle.DTO.ClubDTO.*;
import likelion12.puzzle.DTO.MemberDTO.*;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.domain.JoinClub;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.service.ClubService;
import likelion12.puzzle.service.JoinClubService;
import likelion12.puzzle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;
    private final MemberService memberService;
    private final JoinClubService joinClubService;

    @PostMapping("/club/add")
    public Club addClub(@RequestBody Club club) {
        return clubService.addNewClub(club.getName(), club.getDescription(), club.getLogo());
    }

    @PostMapping("/club/add/{studentId}")
    public ResponseJoinClub addJoinClub(@RequestBody RequestJoinClub request, @PathVariable("studentId") String studentId) {
        Club club = clubService.findByName(request.getClubName());
        JoinClub joinClub = joinClubService.saveNewMember(studentId, club);

        return new ResponseJoinClub(studentId, club.getName());
    }
}