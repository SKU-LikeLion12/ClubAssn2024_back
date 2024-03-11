package likelion12.puzzle.DTO;

import likelion12.puzzle.domain.Club;
import likelion12.puzzle.domain.Item;
import likelion12.puzzle.domain.ItemRent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

public class ItemRentDTO {

    @AllArgsConstructor
    public static class MemberRentingSize {
        public Set<Long> variety;
        public int count;
    }

    public enum DelayState {
        NO_DELAY, DELAY, LONG_DELAY
    }

    @Data
    public static class BookDTO {
        Long itemRentId;
        String studentId;
        String itemName;
        byte[] image;
        Integer count;
        LocalDateTime bookTime;
        LocalDateTime needReceiveTime;
        public BookDTO(ItemRent itemRent, LocalDateTime needReceiveTime){
            this.itemRentId = itemRent.getId();
            this.studentId = itemRent.getRenter().getStudentId();
            this.itemName = itemRent.getItem().getName();
            this.image = itemRent.getItem().getImage();
            this.count = itemRent.getCount();
            this.bookTime = itemRent.getOfferDate();
            this.needReceiveTime = needReceiveTime;
        }
    }

    @Data
    public static class RentDTO {
        Long itemRentId;
        String studentId;
        String itemName;
        byte[] image;
        Integer count;
        LocalDateTime rentTime;
        LocalDateTime needReturnTime;
        DelayState state;
        public RentDTO(ItemRent itemRent, LocalDateTime needReturnTime, DelayState state){
            this.itemRentId = itemRent.getId();
            this.studentId = itemRent.getRenter().getStudentId();
            this.itemName = itemRent.getItem().getName();
            this.image = itemRent.getItem().getImage();
            this.count = itemRent.getCount();
            this.rentTime = itemRent.getReceiveDate();
            this.needReturnTime = needReturnTime;
            this.state = state;
        }
    }

    @Data
    @AllArgsConstructor
    public static class AdminBookListDTO{
        Long itemRentId;
        String studentId;
        String itemName;
        String iconClub;
        Integer count;
        LocalDateTime bookTime;

//        public AdminBookListDTO(Long itemRentId, String studentId, String itemName, String iconClub, Integer count, LocalDateTime bookTime) {
//            this.itemRentId = itemRentId;
//            this.studentId = studentId;
//            this.itemName = itemName;
//            this.iconClub = iconClub;
//            this.count = count;
//            this.bookTime = bookTime;
//        }
    }

    @Getter
    @AllArgsConstructor
    public static class ReceiveDTO {
        ItemRent itemRent;
        LocalDateTime needReturnTime;
    }

    @Data
    public static class BookRequestDTO{
        MemberDTO.MemberToken token;
        long itemId;
        int count;
    }

    @Data
    @AllArgsConstructor
    public static class RestItemListDTO{
        Long id;
        String name;
        Integer count;
        byte[] image;
        Integer rentingCount;
        Long bookingCount;
    }

}
