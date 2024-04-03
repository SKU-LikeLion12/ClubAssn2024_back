package likelion12.puzzle.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.security.JwtUtility;
import likelion12.puzzle.service.JoinClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JoinClubController {
    private final JoinClubService joinClubService;
    private final JwtUtility jwtUtility;

    @GetMapping("/joined-list")
    public List<Club> findJoinedClubByMemberId(HttpServletRequest header){
        String studentId = jwtUtility.getStudentId(header.getHeader("Authorization"));
        return joinClubService.findJoinedClubByMemberId(studentId);
    }
}
