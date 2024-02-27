package likelion12.puzzle;

import likelion12.puzzle.service.DateCheckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TmpTest {
    @Autowired
    private DateCheckService dateCheckService;

    @Test
    @Transactional
    @Rollback(false) // 롤백 비활성화
    public void test(){
        System.out.println("dateCheckService.expireOfferDate() = " + dateCheckService.expireOfferDate());
    }
}
