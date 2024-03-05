package likelion12.puzzle.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Event {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name; // 행사 이름

    //    @Column(nullable = false) // 아직 어떻게 넣을지 몰라서
    private String image; // 행사 포스터, 자료형은 미정

    //    @Column(nullable = false) // 날짜 어떻게 넣을지 몰라서
    private LocalDateTime date; // 행사 날짜

    public Event(String name, String image, LocalDateTime date) {
        this.name = name;
        this.image = image;
        this.date = date;
    }
}