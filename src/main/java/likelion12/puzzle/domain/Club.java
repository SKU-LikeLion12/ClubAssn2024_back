package likelion12.puzzle.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Club {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;
    private String description;
    private String logo;

    public Club(String clubName, String description, String logo) {
        this.name = clubName;
        this.description = description;
        this.logo = logo;
    }
}