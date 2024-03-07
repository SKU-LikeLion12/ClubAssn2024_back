package likelion12.puzzle.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
    LocalDateTime offerDate;
    @Column(name = "rent_start_date")
    LocalDateTime receiveDate;
    @Column(name = "rent_return_date")
    LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    private RentStatus status;

//    private TimeStatus tStatus;//해당없읍, 지연, 장기지연



    public static final long longDelayTime = (7 * 24 * 60 * 60 * 1000);

    public ItemRent(Member member, Item item, int count){
        this.renter = member;
        this.item = item;
        this.count = count;
        this.offerDate = getNow();
        this.status = RentStatus.BOOK;
    }

    public void cancel(){
        this.status = RentStatus.CANCEL;
    }

    public void itemReceive(LocalDateTime receiveTime){
        this.receiveDate = receiveTime;
        this.status = RentStatus.RENT;
        this.item.rentItem(this.count);
    }

    public void itemReturn(LocalDateTime returnTime, RentStatus status){
        this.returnDate = returnTime;
        this.status = status;
        this.item.returnItem(this.count);
    }

    public static LocalDateTime getNow(){
        return LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

}
