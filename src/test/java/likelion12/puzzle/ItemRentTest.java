package likelion12.puzzle;

import jakarta.persistence.EntityManager;
import likelion12.puzzle.domain.Item;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.service.ItemRentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ItemRentTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private ItemRentService itemRentService;

    @Test
    @DisplayName("물품종류초과 체크")
    @Transactional
    public void checkVariety(){
        Member member = new Member(20240000,"test",null);
        em.persist(member);

        for(int i=0; i<4; i++){
            Item item = new Item("물건",3);
            em.persist(item);
            itemRentService.bookItem(member.getStudentId(), item.getId(),1);
        }

    }

    @Test
    @DisplayName("물품종류초과 체크")
    @Transactional
    public void checkReceive(){
        Member member = new Member(20240000,"test",null);
        em.persist(member);

        for(int i=0; i<4; i++){
            Item item = new Item("물건",3);
            em.persist(item);
            itemRentService.bookItem(member.getStudentId(), item.getId(),1);
        }

    }
}
