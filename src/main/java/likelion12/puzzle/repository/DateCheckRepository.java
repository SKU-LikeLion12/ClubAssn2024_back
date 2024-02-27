package likelion12.puzzle.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
        try{
            return em.createQuery("select dc from DateCheck dc where dc.date = :date", DateCheck.class)
                    .setParameter("date", date).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public void remove(DateCheck date){
        em.remove(date);
    }
}