package likelion12.puzzle.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class EventDTO {


    @Data
    public static class ResponseEvent {
        @Schema(description = "이벤트 번호", example = "1")
        private Long id;

        @Schema(description = "이벤트 이름", example = "동아리페스티벌")
        private String name;

        @Schema(description = "이벤트 일자", example = "2024-03-31")
        private LocalDateTime date;

        @Schema(description = "이벤트 포스터", example = "")
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
        private MultipartFile image;
    }

    @Data
    public static class ResponsePuzzleForNotPart {
        @Schema(description = "이벤트 번호", example = "1")
        private Long id;

        @Schema(description = "이벤트 이름", example = "동아리페스티벌")
        private String name;

        public ResponsePuzzleForNotPart(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Data
    @AllArgsConstructor
    public static class EventAllRequestExceptImage {
        @Schema(description = "이벤트 번호", example = "1")
        private Long id;

        @Schema(description = "이벤트 이름", example = "동아리페스티벌")
        private String name;

        @Schema(description = "이벤트 일자", example = "2024-03-31")
        private LocalDateTime date;
    }
}
