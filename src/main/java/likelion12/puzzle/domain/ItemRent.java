package likelion12.puzzle.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class ItemRent {

    @Id @GeneratedValue @Column(name = "rent_item_id")
    Long id;

    @ManyToOne @JoinColumn(name="renter_id")
    Member renter;
    @ManyToOne @JoinColumn(name = "item_id")
    Item item;

    Integer count;

    @Column(name = "rent_offer_date")
    LocalDateTime rentOfferDate;
    @Column(name = "rent_start_date")
    LocalDateTime rentStartDate;
    @Column(name = "rent_return_date")
    LocalDateTime rentReturnDate;

    @Column(name = "need_receive_date")
    LocalDateTime needReceiveDate;
    @Column(name = "need_return_date")
    LocalDateTime needReturnDate;

    @Enumerated(EnumType.STRING)
    private RentStatus status;

    public ItemRent(Member member, Item item, int count, LocalDateTime needReceiveDate){
        this.renter = member;
        this.item = item;
        this.count = count;
        this.rentOfferDate = LocalDateTime.now();
        this.needReceiveDate = needReceiveDate;
        this.status = RentStatus.BOOK;
    }

}
