package likelion12.puzzle.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

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
    @AllArgsConstructor
    public static class ClubCreateRequest{
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

