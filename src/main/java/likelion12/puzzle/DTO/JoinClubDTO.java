package likelion12.puzzle.DTO;

import lombok.Data;

@Data
public class JoinClubDTO {

    @Data
    public static class CreateJC {
        private String studentId;
        private String clubName;

        public CreateJC(String studentId, String clubName) {
            this.studentId = studentId;
            this.clubName = clubName;
        }
    }

    @Data
    public static class DeleteJC {
        private String memberId;
        private String clubName;
    }
}