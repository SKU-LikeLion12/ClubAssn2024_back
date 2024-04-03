package likelion12.puzzle.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.domain.RoleType;
import likelion12.puzzle.service.ImageUtility;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public class MemberDTO {
    @Data
    public static class ResponseMember {
        private String studentId;
        private String name;
        private String clubName;

        public ResponseMember(String studentId, String name, String clubName) {
            this.studentId = studentId;
            this.name = name;
            this.clubName = clubName;
        }
    }

    // 로그인 할 때
    @Data
    public static class RequestMember {
        @Schema(description = "학번", example = "00000000")
        private String studentId;

        @Schema(description = "이름", example = "홍길동")
        private String name;
    }

    @Data
    public static class RequestAgree {
        private String studentId;
        private String name;
        private Boolean isAgree;
    }

    @Data
    public static class ResponseMain {
        private String name;
        private String clubName;
        private String logo;

        public ResponseMain(Member member) {
            this.name = member.getName();
            this.clubName = member.getIconClub().getName();
            this.logo = member.getIconClub().arrayToImage();
        }
    }

    @Data
    public static class ResponseLogin {
        @Schema(description = "토큰", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDAwMDAwMCIsImlhdCI6MTcxMTgxMDc2NSwiZXhwIjoyMDcxODEwNzY1fQ.2gbH5s0ODmTE59NRrFi9Fd8kqahHsfQqgHu6NQjjte1_4abMHmI6VfSKVI46SjftueKXSDFVr8WATiuf1ZMNzg")
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
