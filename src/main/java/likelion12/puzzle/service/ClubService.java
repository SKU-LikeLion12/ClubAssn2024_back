package likelion12.puzzle.service;

import likelion12.puzzle.domain.Club;
import likelion12.puzzle.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubService {
    private final ClubRepository clubRepository;

    // 동아리 추가
    @Transactional
    public Club addNewClub(String clubName, String description, byte[] logo) {
        Club club = new Club(clubName, description, logo);
        return clubRepository.addNewClub(club);
    }

    public Club findById(Long id){
        return clubRepository.findById(id);
    }

    public List<Club> findAll() {
        return clubRepository.findAll();
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

    @Transactional
    public Club changeClub (Long clubId, String clubName, String description, MultipartFile logo) throws IOException {

        byte[] imageBytes = null;
        Club club = findById(clubId);
        if (logo != null) {
            imageBytes = logo.getBytes();
        } else {
            imageBytes = club.getLogo();
        }
        club.setName(clubName);
        club.setDescription(description);
        club.setLogo(imageBytes);
        return club;
    }
}