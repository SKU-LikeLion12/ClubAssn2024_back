package likelion12.puzzle.DTO;

import lombok.Data;

import java.time.LocalDateTime;

public class JoinEventDTO {
    @Data
    public static class ResponseJoinEvent {
        private Long id;
        private String name;
        private LocalDateTime addedTime;

        public ResponseJoinEvent(Long id, String name, LocalDateTime dateTime) {
            this.id = id;
            this.name = name;
            this.addedTime = dateTime;
        }
    }

    @Data
    public static class RequestJoinEvent {
        private Long id;
        private String name;
        private LocalDateTime addDate;
    }

    @Data
    public static class RequestJoinEventForDelete {
        private Long id;
        private String name;
    }
}
