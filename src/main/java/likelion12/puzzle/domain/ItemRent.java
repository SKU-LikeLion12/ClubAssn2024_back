package likelion12.puzzle.domain;


import jakarta.persistence.*;
import likelion12.puzzle.service.DateCheckService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

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

    @Enumerated(EnumType.STRING)
    private RentStatus status;



    long longDelayTime = (7 * 24 * 60 * 60 * 1000);

    public ItemRent(Member member, Item item, int count){
        this.renter = member;
        this.item = item;
        this.count = count;
        this.rentOfferDate = getNow();
        this.status = RentStatus.BOOK;
    }

    public void cancel(){
        this.status = RentStatus.CANCEL;
    }

    public void itemReceive(LocalDateTime receiveTime){
        this.rentStartDate = receiveTime;
        this.status = RentStatus.RENT;
    }

    public void checkDelay(LocalDateTime needReturnTime){
        LocalDateTime now = getNow();
        if (needReturnTime.isAfter(now)) {
            long daysDiff = Duration.between(now, needReturnTime).toMillis();
            if (daysDiff >= longDelayTime) {
                this.status = RentStatus.LONG_DELAY_RENT;
            } else {
                this.status = RentStatus.DELAY_RENT;
            }
        }
    }

    public void checkAutoCancel(LocalDateTime needReceiveTime){
        LocalDateTime now = getNow();
        if(needReceiveTime.isAfter(now)){
            this.status = RentStatus.CANCEL;
        }
    }

    public void itemReturn(LocalDateTime returnTime, LocalDateTime needReturnTime){
        this.rentReturnDate = returnTime;
        if (needReturnTime.isAfter(returnTime)) {
            long daysDiff = Duration.between(returnTime, needReturnTime).toMillis();
            if (daysDiff >= longDelayTime) {
                this.status = RentStatus.LONG_DELAY_RETURN;
            } else {
                this.status = RentStatus.DELAY_RETURN;
            }
        }else{
            this.status = RentStatus.RETURN;
        }
    }

    private LocalDateTime getNow(){
        return LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

}
