package likelion12.puzzle.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class DateCheck {
    @Id @GeneratedValue
    Long id;

    LocalDate date;
    Boolean isBreak;

    public DateCheck(LocalDate date, Boolean isBreak){
        this.date = date;
        this.isBreak = isBreak;
    }
}
