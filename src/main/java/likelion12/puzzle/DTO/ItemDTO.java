package likelion12.puzzle.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


public class ItemDTO {
    @Data
    @AllArgsConstructor
    public static class ItemCreateResponse{
        @NotEmpty
        private String name;
        private int count;
        @NotEmpty
        private String image;
    }

    @Data
    @AllArgsConstructor
    public static class ItemCreateRequest{
        @NotEmpty
        private String name;
        private int count;
        @NotEmpty
        private MultipartFile image;
    }

    @Data
    @AllArgsConstructor
    public static class ItemUpdateRequest{
        private String name;
        private int count;
        private MultipartFile image;
    }

    @Data
    @AllArgsConstructor
    public static class ItemAllRequestExceptImage {
        private Long id;
        private String name;
        private int count;
    }

}

