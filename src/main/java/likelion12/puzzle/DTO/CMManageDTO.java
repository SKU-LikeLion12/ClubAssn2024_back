package likelion12.puzzle.DTO;

import lombok.Data;


@Data
public class CMManageDTO {
        private String studentId;
        private String studentName;
        private String joinClubs;

        public CMManageDTO(String studentId, String studentName, String joinClubs) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.joinClubs = joinClubs;
        }
}
