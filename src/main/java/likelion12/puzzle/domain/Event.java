package likelion12.puzzle.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Event {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name; // 행사 이름

    @Column(nullable = false) // 아직 어떻게 넣을지 몰라서
    private byte[] poster; // 행사 포스터, 자료형은 미정

    private LocalDateTime date; // 행사 날짜

    public Event(String name, byte[] poster, LocalDateTime date) {
        this.name = name;
        this.poster = poster;
        this.date = date;
    }

    public void setPoster(MultipartFile poster) throws IOException {
        this.poster = poster.getBytes();
    }

    public void changeEvent(String name, LocalDateTime date) {
        this.name = name;
        this.date = date;
    }
}

