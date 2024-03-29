package likelion12.puzzle.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import likelion12.puzzle.DTO.ItemDTO;
import likelion12.puzzle.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public Item save(Item item){
        em.persist(item);
        return item;
    }

    public void delete(Item item) {
        em.remove(item);
    }

    public Item findById(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

    public List<Item.ItemAllRequest> findAllExceptImage() {
        return em.createQuery("SELECT new ItemAllRequest(i.id, i.name, i.count) FROM Item i", Item.ItemAllRequest.class)
                .getResultList();
    }

    public Item findByName(String name){
        try{
            return em.createQuery("select i from Item i where i.name = :name", Item.class)
                    .setParameter("name",name).getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

}
