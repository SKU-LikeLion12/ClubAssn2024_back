package likelion12.puzzle;

import jakarta.persistence.EntityManager;
import likelion12.puzzle.DTO.ItemRentDTO;
import likelion12.puzzle.domain.Club;
import likelion12.puzzle.domain.Item;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.domain.RentStatus;
import likelion12.puzzle.service.DateCheckService;
import likelion12.puzzle.service.ItemRentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ItemRentTest {

//    @Autowired
//    private EntityManager em;
//    @Autowired
//    private ItemRentService itemRentService;
//    @Autowired
//    private DateCheckService dateCheckService;
//
//    @Test
//    @DisplayName("물품종류초과 체크")
//    @Transactional
//    @Rollback(value = false)
//    public void checkVariety(){
//        Member member = new Member("00000000","test",null);
//        em.persist(member);
//
//        for(int i=0; i<3; i++){
//            Item item = new Item("물건",3,null);
//            em.persist(item);
//            itemRentService.bookItem(member.getStudentId(), item.getId(),1);
//        }
//
//    }
//
////    @Test
////    @DisplayName("물품종류초과 체크")
////    @Transactional
////    public void checkReceive(){
////        Member member = new Member("20240000","test",null);
////        em.persist(member);
////
////        for(int i=0; i<4; i++){
////            Item item = new Item("물건",3,null);
////            em.persist(item);
////            itemRentService.bookItem(member.getStudentId(), item.getId(),1);
////        }
////
////    }
//
//    @Test
//    @DisplayName("모르겠으니 대충 때려박겠음")
//    @Transactional
//    @Rollback(value = false)
//    public void dateCheckerUpdate(){
//        LocalDate localDate = LocalDate.now().minusDays(10);
//        int day = 10;
//        while(day>0){
//            day--;
//            dateCheckService.getDayCheck(localDate);
//            localDate = localDate.plusDays(1);
//        }
//
//    }
//
//    @Test
//    @Transactional
////    @Rollback(value = false)
//    public void datpasfddate(){
//        Club club = new Club("클럽2",null,null);
//        em.persist(club);
//
//        Member member1 = new Member("1", "학생1", null);
//        Member member2 = new Member("2", "학생2", null);
//
//        Item item = new Item("책", 10,null);
//
//        em.persist(member1);
//        em.persist(member2);
//        em.persist(item);
//
//        itemRentService.bookItem(member1.getStudentId(), item.getId(), 1);
//        itemRentService.bookItem(member2.getStudentId(), item.getId(),1);
//
//
//
//        List<ItemRentDTO.AdminBookListDTO> results = em.createQuery("select new AdminBookListDTO(ir.id, ir.renter.studentId, ir.item.name, ir.renterClub, ir.count, ir.offerDate) " +
//                        "from ItemRent ir where ir.status = :status or ir.offerDate >= :time", ItemRentDTO.AdminBookListDTO.class)
//                .setParameter("status", RentStatus.BOOK).setParameter("time",LocalDateTime.now())
//                .getResultList();
//
//        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
//        for (ItemRentDTO.AdminBookListDTO result : results) {
//            System.out.println(result);
//        }
//
//    }
}
