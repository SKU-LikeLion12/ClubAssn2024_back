package likelion12.puzzle.service;

import likelion12.puzzle.DTO.CMManageDTO;
import likelion12.puzzle.repository.CMManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CMManageService {
    private final CMManageRepository cmManageRepository;

    public List<CMManageDTO> searchByKeyword(String keyword) {
        return cmManageRepository.findCMManageByKeyword(keyword);
    }
}


