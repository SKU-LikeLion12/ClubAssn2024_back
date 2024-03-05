package likelion12.puzzle.repository;

import jakarta.persistence.EntityManager;
import likelion12.puzzle.DTO.CMManageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CMManageRepository {

    private final EntityManager em;

    public List<CMManageDTO> findCMManageByKeyword(String keyword) {
        return em.createQuery("SELECT new likelion12.puzzle.DTO.CMManageDTO(m.studentId, m.name, c.name) " +
                        "FROM JoinClub jc " +
                        "INNER JOIN jc.member m " +
                        "INNER JOIN jc.club c " +
                        "WHERE m.name LIKE :keyword OR m.studentId LIKE :keyword OR c.name LIKE :keyword", CMManageDTO.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }



}
