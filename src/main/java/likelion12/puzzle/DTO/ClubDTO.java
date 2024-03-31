package likelion12.puzzle.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

public class ClubDTO {

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
