package likelion12.puzzle.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class ClubDTO {
    @Data
    public static class ResponseJoinClub {
        private String studentId;
        private String clubName;

        public ResponseJoinClub(String studentId, String clubName) {
            this.studentId = studentId;
            this.clubName = clubName;
        }
    }

    @Data
    public static class RequestJoinClub {
        private String clubName;
    }

    @Data
    public static class ResponseClub {
        private Long id;
        private String name;
        private String description;
        private String logo;

        public ResponseClub(Long id, String name, String description, String logo) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.logo = logo;
        }
    }

    @Data
    @AllArgsConstructor
    public static class ClubCreateRequest{
        @NotEmpty
        private String name;
        @NotEmpty
        private String description;
        @NotEmpty
        private MultipartFile logo;
    }

    @Data
    @AllArgsConstructor
    public static class ClubUpdateRequest{
        @NotEmpty
        private String name;
        @NotEmpty
        private String description;
        @NotEmpty
        private String logo;
    }

    @Data
    @AllArgsConstructor
    public static class ClubAllRequest {
        private Long id;
        private String name;
        private String description;
    }
}

