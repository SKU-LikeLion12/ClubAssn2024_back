package likelion12.puzzle.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "club_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Club iconClub;

    // 하나의 Member 엔티티가 여러 개의 JoinClub 엔티티와 연관될 수 있으니..
//    @OneToMany(mappedBy = "member")
//    private List<JoinClub> joinClubs = new ArrayList<>();

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String name;
    private boolean isAgree = false;

    public Member(String studentId, String name, Club iconClub) {
        this.studentId = studentId;
        this.name = name;
        this.iconClub = iconClub;
    }

    public Member(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public void updateIconClub(Club newIconClub) {
        this.iconClub = newIconClub;

//        return member;
    }
}