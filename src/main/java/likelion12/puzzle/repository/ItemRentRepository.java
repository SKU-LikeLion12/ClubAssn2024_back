package likelion12.puzzle.repository;

import jakarta.persistence.EntityManager;
import likelion12.puzzle.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class ItemRentRepository {
    private final EntityManager em;

    public ItemRent save(ItemRent rentItem){
        em.persist(rentItem);
        return rentItem;
    }

    public ItemRent findById(Long id){
        return em.find(ItemRent.class, id);
    }

    public List<ItemRent> findByMember(Member member){
        return em.createQuery("select ir from ItemRent ir where ir.renter = :member", ItemRent.class)
                .setParameter("member",member).getResultList();
    }

    public List<ItemRent> findByItem(Item item){
        return em.createQuery("select ir from ItemRent ir where ir.item = :item", ItemRent.class)
                .setParameter("item",item).getResultList();
    }

    public List<ItemRent> findByStatus(RentStatus status){
        return em.createQuery("select ir from ItemRent ir where ir.status = :status", ItemRent.class)
                .setParameter("status",status).getResultList();
    }
//
//    public List<ItemRent> findByMemberStatus(Member member, RentStatus status){
//        return em.createQuery("select ir from ItemRent ir where ir.renter = :member and ir.status = :status", ItemRent.class)
//                .setParameter("status",status).setParameter("member", member).getResultList();
//    }

    public List<ItemRent> findByMemberStatus(Member member, Set<RentStatus> statusGroup) {
        return em.createQuery("SELECT ir FROM ItemRent ir WHERE ir.renter = :member AND ir.status IN :statusGroup", ItemRent.class)
                .setParameter("member", member)
                .setParameter("statusGroup", statusGroup)
                .getResultList();
    }

//    public List<ItemRent> findByItemStatus(Item item, RentStatus status){
//        return em.createQuery("select ir from ItemRent ir where ir.item = :item and ir.status = :status", ItemRent.class)
//                .setParameter("status",status).setParameter("item", item).getResultList();
//    }

    public List<ItemRent> findByItemStatus(Item item, Set<RentStatus> statusGroup) {
        return em.createQuery("SELECT ir FROM ItemRent ir WHERE ir.item = :item AND ir.status IN :statusGroup", ItemRent.class)
                .setParameter("item", item)
                .setParameter("statusGroup", statusGroup)
                .getResultList();
    }


    public List<ItemRent> findAll(){
        return em.createQuery("select ir from ItemRent ir", ItemRent.class).getResultList();
    }

//    public List<ItemRent> findMemberRenting(Member member){
//        return em.createQuery("select ir from ItemRent ir where ir.renter = :member and (ir.status = :book or ir.status = :rent)", ItemRent.class)
//                .setParameter("member",member).setParameter("book",RentStatus.BOOK).setParameter("rent",RentStatus.RENT).getResultList();
//    }

//    public List<ItemRent> findItemRenting(Item item){
//        return em.createQuery("select ir from ItemRent ir where ir.item = :item and (ir.status = :book or ir.status = :rent)", ItemRent.class)
//                .setParameter("item",item).setParameter("book",RentStatus.BOOK).setParameter("rent",RentStatus.RENT).getResultList();
//    }

//    public List<ItemRent> findByItemStatusTime(Item item, Set<RentStatus> statusGroup, LocalDateTime localDateTime){}

    public void delete(ItemRent itemRent){
        em.remove(itemRent);
    }



}
