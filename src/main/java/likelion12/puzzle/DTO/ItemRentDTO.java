package likelion12.puzzle.DTO;

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

    @Getter
    @AllArgsConstructor
    public static class BookDTO {
        ItemRent itemRent;
        LocalDateTime needReceiveTime;
    }

    @Getter
    @AllArgsConstructor
    public static class ReceiveDTO {
        ItemRent itemRent;
        LocalDateTime needReturnTime;
    }

    @Data
    public static class BookRequestDTO{
        String studentId;
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
