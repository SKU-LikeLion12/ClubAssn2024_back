package likelion12.puzzle.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class JoinClub {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "club_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Club club;

    public JoinClub(Club club, Member member) {
        this.club = club;
        this.member = member;
    }
}