package likelion12.puzzle.DTO;

import lombok.Data;

import java.time.LocalDateTime;

public class EventDTO {


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
}
