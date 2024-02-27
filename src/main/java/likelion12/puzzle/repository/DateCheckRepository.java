package likelion12.puzzle.repository;

import jakarta.persistence.EntityManager;
import likelion12.puzzle.domain.DateCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DateCheckRepository {
    private final EntityManager em;
    public DateCheck save(DateCheck date){
        em.persist(date);
        return date;
    }

    public DateCheck findById(Long id){
        return em.find(DateCheck.class, id);
    }

    public DateCheck findByDate(LocalDate date){
        List<DateCheck> data = em.createQuery("select dc from DateCheck dc where dc.date = :date", DateCheck.class)
                .setParameter("date", date).getResultList();
        if(data.isEmpty()) return null;
        return data.get(0);
    }

    public void remove(DateCheck date){
        em.remove(date);
    }
}
