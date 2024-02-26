package likelion12.puzzle.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Item {

    @Id @GeneratedValue @Column(name="item_id")
    Long id;

    String name;
    Integer count;
    String discription;

}
