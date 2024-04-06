package likelion12.puzzle;


import jakarta.persistence.EntityManager;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.domain.RoleType;
import likelion12.puzzle.service.DateCheckService;
import likelion12.puzzle.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
public class TmpTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    @Rollback(false) // 롤백 비활성화
    public void test(){
        Member member = new Member("00000000","홍길동", RoleType.ROLE_ADMIN);
        em.persist(member);
    }
}
