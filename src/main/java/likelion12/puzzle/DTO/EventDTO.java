package likelion12.puzzle.DTO;

import likelion12.puzzle.domain.JoinEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public class EventDTO {
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

    @Data
    public static class ResponseEvent {
        private Long id;
        private String name;
        private LocalDateTime date;
        private String image;

        public ResponseEvent(Long id, String name, String image, LocalDateTime date) {
            this.id = id;
            this.name = name;
            this.image = image;
            this.date = date;
        }
    }

    @Data
    public static class RequestEvent {
        private String name;
        private LocalDateTime date;
        private String image;
    }

    @Data
    public static class ResponsePuzzleForNotPart {
        private Long id;
        private String name;

        public ResponsePuzzleForNotPart(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Data
    @AllArgsConstructor
    public static class EventAllRequestExceptImage {
        private Long id;
        private String name;
        private LocalDateTime date;
    }
}
