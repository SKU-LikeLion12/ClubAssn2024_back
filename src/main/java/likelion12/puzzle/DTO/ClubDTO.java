package likelion12.puzzle.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class ClubDTO {
    @Data
    public static class ResponseJoinClub {

        @Schema(description = "학번", example = "00000000")
        private String studentId;

        @Schema(description = "동아리 이름", example = "멋쟁이사자처럼")
        private String clubName;

        public ResponseJoinClub(String studentId, String clubName) {
            this.studentId = studentId;
            this.clubName = clubName;
        }
    }

    @Data
    public static class RequestJoinClub {
        @Schema(description = "동아리 이름", example = "멋쟁이사자처럼")
        private String clubName;
    }

    @Data
    public static class ResponseClub {
        @Schema(description = "동아리 번호", example = "1")
        private Long id;
        @Schema(description = "동아리 이름", example = "멋쟁이사자처럼")
        private String name;
        @Schema(description = "동아리 설명", example = "국내최대규모 IT창업동아리")
        private String description;
        @Schema(description = "동아리 로고", example = "")
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
        @Schema(description = "동아리 이름", example = "멋쟁이사자처럼")
        private String name;
        @NotEmpty
        @Schema(description = "동아리 설명", example = "국내최대규모 IT창업동아리")
        private String description;
        @NotEmpty
        @Schema(description = "동아리 로고", example = "")
        private String logo;
    }

    @Data
    @AllArgsConstructor
    public static class ResponseClubCreate{
        @NotEmpty
        @Schema(description = "동아리 이름", example = "멋쟁이사자처럼")
        private String name;
        @NotEmpty
        @Schema(description = "동아리 설명", example = "국내최대규모 IT창업동아리")
        private String description;
        @NotEmpty
        @Schema(description = "동아리 로고", example = "")
        private MultipartFile logo;
    }

    @Data
    @AllArgsConstructor
    public static class ResponseClubUpdate{
        @Schema(description = "동아리 번호", example = "1")
        private Long id;
        @NotEmpty
        @Schema(description = "동아리 이름", example = "멋쟁이사자처럼")
        private String name;
        @NotEmpty
        @Schema(description = "동아리 설명", example = "국내최대규모 IT창업동아리")
        private String description;
        @NotEmpty
        @Schema(description = "동아리 로고", example = "")
        private MultipartFile logo;
    }


    @Data
    @AllArgsConstructor
    public static class ClubUpdateRequest{
        @NotEmpty
        @Schema(description = "동아리 이름", example = "멋쟁이사자처럼")
        private String name;

        @NotEmpty
        @Schema(description = "동아리 설명", example = "국내최대규모 IT창업동아리")
        private String description;

        @NotEmpty
        @Schema(description = "동아리 로고", example = "")
        private String logo;
    }

    @Data
    @AllArgsConstructor
    public static class ClubAllRequest {
        @Schema(description = "동아리 이름", example = "멋쟁이사자처럼")
        private String name;

        @Schema(description = "동아리 설명", example = "국내최대규모 IT창업동아리")
        private String description;
    }
}

