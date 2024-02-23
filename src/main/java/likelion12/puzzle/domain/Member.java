package likelion12.puzzle.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "club_id")
    private Club iconClub;

    @Column(nullable = false)
    private int studentId;

    @Column(nullable = false)
    private String name;
    private boolean isAgree = false;

    public Member(int studentId, String name, Club iconClub) {
        this.studentId = studentId;
        this.name = name;
        this.iconClub = iconClub;
        this.isAgree = false;
    }
}