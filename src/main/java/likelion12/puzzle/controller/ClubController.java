package likelion12.puzzle.controller;

import likelion12.puzzle.domain.Club;
import likelion12.puzzle.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;
}
