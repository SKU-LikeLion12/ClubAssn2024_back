package likelion12.puzzle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {
    @GetMapping("/events")
    public void myEvents() {
        // 로그인 되어있으면 실행. 토큰 없으면 알림 후 로그인 페이지로 이동

        // 로그인 되어있으면 member(이름, 로고, 동아리)
        // joinevent 보고 member id 있는거에서 event id로 행사 id, name, image, date 뿌려주기
    }
}
