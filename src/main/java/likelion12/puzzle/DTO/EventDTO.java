package likelion12.puzzle.DTO;

import likelion12.puzzle.domain.JoinEvent;
import lombok.Data;

import java.time.LocalDateTime;

public class EventDTO {
    @Data
    public static class ResponseJoinEvent {
        private Long id;
        private String name;
        private LocalDateTime addedTime;

        public ResponseJoinEvent(JoinEvent joinEvent) {
            this.name = joinEvent.getEvent().getName();
            this.addedTime = joinEvent.getCheckDate();
        }
    }

    @Data
    public static class RequestJoinEvent {
        private Long id;
        private String name;
        private LocalDateTime addDate;
    }


    @Data
    public static class ResponseEvent {
        private Long id;
        private String name;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String image;
    }

    @Data
    public static class RequestEvent {
        private Long id;
        private String name;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String image;
    }
}
