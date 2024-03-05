package likelion12.puzzle.controller;

import likelion12.puzzle.DTO.CMManageDTO;
import likelion12.puzzle.service.CMManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CMManageController {
    private final CMManageService cmManageService;

    @GetMapping("/search")
    public ResponseEntity<List<CMManageDTO>> search(@RequestParam String keyword) {
        List<CMManageDTO> results = cmManageService.searchByKeyword(keyword);
        if(results.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(results);
        }
    }
}
