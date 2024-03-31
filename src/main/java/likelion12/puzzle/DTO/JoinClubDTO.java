package likelion12.puzzle.DTO;

import lombok.Data;


@Data
public class JoinClubDTO {
        private String studentId;
        private String studentName;
        private String joinClubs;

        public JoinClubDTO(String studentId, String studentName, String joinClubs) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.joinClubs = joinClubs;
        }
}
