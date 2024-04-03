package likelion12.puzzle.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JoinClubDTO {

    @Data
    public static class CreateJC {
        @Schema(description = "학번", example = "00000000")
        private String studentId;

        @Schema(description = "학생 이름", example = "홍길동")
        private String studentName;

        @Schema(description = "동아리 이름", example = "멋쟁이사자처럼")
        private String clubName;

        public CreateJC(String studentId, String clubName) {
            this.studentId = studentId;
            this.clubName = clubName;
        }
    }

    @Data
    public static class DeleteJC {
        @Schema(description = "학번", example = "00000000")
        private String memberId;

        @Schema(description = "동아리이름", example = "멋쟁이사자처럼")
        private String clubName;
    }
}