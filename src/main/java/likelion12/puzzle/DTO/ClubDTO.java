package likelion12.puzzle.DTO;

import likelion12.puzzle.domain.Club;
import likelion12.puzzle.domain.Member;
import lombok.Data;

public class ClubDTO {
    @Data
    public static class ResponseJoinClub {
        private String studentId;
        private String clubName;

        public ResponseJoinClub(String studentId, String clubName) {
            this.studentId = studentId;
            this.clubName = clubName;
        }
    }

    @Data
    public static class RequestJoinClub {
        private String clubName;
    }
}