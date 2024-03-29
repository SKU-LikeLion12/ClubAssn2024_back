package likelion12.puzzle.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


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

//    @Data
//    @AllArgsConstructor
//    public static class ItemAllRequest {
//        private Long id;
//        private String name;
//        private int count;
//    }

}

