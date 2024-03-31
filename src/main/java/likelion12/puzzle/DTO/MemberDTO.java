package likelion12.puzzle.DTO;

import lombok.Data;

public class MemberDTO {
    @Data
    public static class ResponseMember {
        private String studentId;
        private String name;

        public ResponseMember(String studentId, String name) {
            this.studentId = studentId;
            this.name = name;
        }
    }

    @Data
    public static class RequestMember {
        private String studentId;
        private String name;
    }


}
