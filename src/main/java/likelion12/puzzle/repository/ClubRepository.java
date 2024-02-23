package likelion12.puzzle.repository;

import jakarta.persistence.EntityManager;
import likelion12.puzzle.domain.Club;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClubRepository {
    private final EntityManager em;

    // 새로운 동아리 추가
    public Club addNewClub(String clubName, String description, String logo) {
        Club club = new Club(clubName, description, logo);
        em.persist(club);

        return club;
    }

    // 동아리 조회
    public Club findByName(String clubName) {
        return em.createQuery("select c from Club c where c.name =:clubName", Club.class)
                .setParameter("clubName", clubName).getSingleResult();
    }

    // 동아리 삭제
    public boolean deleteClub(String clubName) {
        Club club = findByName(clubName);
        em.remove(club);

        return true;
    }
}