package likelion12.puzzle.service;

import likelion12.puzzle.domain.Club;
import likelion12.puzzle.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubService {
    private final ClubRepository clubRepository;

    // 동아리 추가
    @Transactional
    public Club addNewClub(String clubName, String description, String logo) {
        Club club = new Club(clubName, description, logo);
        return clubRepository.addNewClub(club);
    }

    // 동아리 조회
    public Club findByName(String clubName) {
        return clubRepository.findByName(clubName);
    }

    // 동아리 삭제
    @Transactional
    public boolean deleteClub(String clubName) {
        Club club = clubRepository.findByName(clubName);
        return clubRepository.deleteClub(club);
    }
}