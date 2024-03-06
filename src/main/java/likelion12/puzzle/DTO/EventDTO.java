package likelion12.puzzle.DTO;

import likelion12.puzzle.domain.Event;
import likelion12.puzzle.domain.JoinEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

public class EventDTO {
    @Data
    public static class ResponsePuzzle {
        private Long id;
        private String name;
        private LocalDateTime dateTime;

        public ResponsePuzzle(JoinEvent joinEvent) {
            this.name = joinEvent.getEvent().getName();
            this.dateTime = joinEvent.getCheckDate();
        }
    }

    @Data
    public static class ResponseEvent {
        private Long id;
        private String name;
        private LocalDateTime periodTime;

        public ResponseEvent(Event event) {
            this.id = event.getId();
            this.name = event.getName();
            this.periodTime = event.getDate();
        }
    }

    @Data
    public static class RequestEvent {
        private Long id;
        private String name;
        private LocalDateTime addDate;
    }

    @Data
    @AllArgsConstructor
    public static class EventAllRequestExceptImage {
        private Long id;
        private String name;
        private LocalDateTime date;
    }
}
