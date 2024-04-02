package likelion12.puzzle.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;


public class ItemDTO {
    @Data
    @AllArgsConstructor
    public static class ItemCreateRequest{
        @NotEmpty
        private String name;
        @NotEmpty
        private int count;
        @NotEmpty
        private String image;
    }

    @Data
    @AllArgsConstructor
    public static class ItemAllRequestExceptImage {
        private Long id;
        private String name;
        private int count;
    }

}

