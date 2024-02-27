package likelion12.puzzle.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class RentItem {

    @Id @GeneratedValue @Column(name = "rent_item_id")
    Long id;

    @ManyToOne @JoinColumn(name="renter_id")
    Member renter;
    @ManyToOne @JoinColumn(name = "item_id")
    Item item;

    @Column(name = "rent_offer_date")
    LocalDateTime rentOfferDate;
    @Column(name = "rent_start_date")
    LocalDateTime rentStartDate;
    @Column(name = "rent_return_date")
    LocalDateTime rentReturnDate;
    

}
