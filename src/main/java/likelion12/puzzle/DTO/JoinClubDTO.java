package likelion12.puzzle.DTO;

import lombok.Data;

@Data
public class JoinClubDTO {

    @Data
    public static class CreateJC {
        private String studentId;
        private String studentName;
        private String clubName;

        public CreateJC(String studentId, String studentName, String clubName) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.clubName = clubName;
        }
    }

    @Data
    public static class ResponseJC {
        private String studentId;
        private String studentName;
        private String clubName;
    }

    @Data
    public static class DeleteJC {
        private String memberId;
        private String clubName;
    }

    }
