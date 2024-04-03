package likelion12.puzzle.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import likelion12.puzzle.domain.RoleType;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        @NotEmpty
        @NotNull
        private String studentId;
        @NotEmpty
        @NotNull
        private String name;
    }

    @Data
    public static class ResponseLogin {
        private String accessToken;

        public ResponseLogin(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    @Data
    public static class AddRequestMember {
        private String studentId;
        private String name;
        private RoleType role;
    }

    @Data
    public static class AddMemberResponse {
        private String studentId;
        private String name;
        private RoleType role;

        public AddMemberResponse(String studentId, String name, RoleType role) {
            this.studentId = studentId;
            this.name = name;
            this.role = role;
        }
    }
}
