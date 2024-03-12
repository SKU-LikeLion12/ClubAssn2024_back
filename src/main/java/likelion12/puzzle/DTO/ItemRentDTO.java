package likelion12.puzzle.DTO;

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

    public enum DelayStatus {
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
        DelayStatus state;
        public RentDTO(ItemRent itemRent, LocalDateTime needReturnTime, DelayStatus state){
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
    }

    @Data
    public static class AdminRentListDTO{
        Long itemRentId;
        String studentId;
        String itemName;
        String iconClub;
        Integer count;
        LocalDateTime rentTime;
        DelayStatus status;

        public AdminRentListDTO(Long itemRentId, String studentId, String itemName, String iconClub, Integer count, LocalDateTime rentTime) {
            this.itemRentId = itemRentId;
            this.studentId = studentId;
            this.itemName = itemName;
            this.iconClub = iconClub;
            this.count = count;
            this.rentTime = rentTime;
        }
    }

    @Getter
    public static class ReceiveDTO {
        ItemRent itemRent;
        LocalDateTime needReturnTime;

        public ReceiveDTO(ItemRent itemRent, LocalDateTime needReturnTime) {
            this.itemRent = itemRent;
            this.needReturnTime = needReturnTime;
        }
    }

    @Data
    public static class BookRequestDTO{
        MemberDTO.MemberToken token;
        long itemId;
        int count;
    }

    @Data
    public static class CancelRequestDTO {
        MemberDTO.MemberToken token;
        long itemRentId;
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

    @Data
    public static class RequestItemRent{
        Long itemRentId;
    }

}
