package likelion12.puzzle.DTO;

import lombok.Data;

import java.util.List;

@Data
public class MemberClubDTO {
    private Long memberId;
    private String name;
    private String studentId;
    private List<String> clubs;

    public MemberClubDTO(Long memberId, String name, String studentId, List<String> clubs) {
        this.memberId = memberId;
        this.name = name;
        this.studentId = studentId;
        this.clubs = clubs;
    }
}
