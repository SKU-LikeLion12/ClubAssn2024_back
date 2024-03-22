//package likelion12.puzzle;
//
//import jakarta.persistence.EntityManager;
//import jdk.jfr.Name;
//import likelion12.puzzle.domain.Member;
//import likelion12.puzzle.service.DateCheckService;
//import likelion12.puzzle.service.ItemRentService;
//import likelion12.puzzle.service.ItemService;
//import likelion12.puzzle.service.MemberService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//
//@SpringBootTest
//public class TmpTest {
//    @Autowired
//    private DateCheckService dateCheckService;
//
//    @Test
//    @Transactional
//    @Rollback(false) // 롤백 비활성화
//    public void test(){
//        System.out.println("dateCheckService.expireOfferDate() = " + dateCheckService.needReceiveDate(LocalDateTime.now()));
//    }
//}
