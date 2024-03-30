package likelion12.puzzle.DTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema(description = "대여번호", example = "1")
        Long itemRentId;

        @Schema(description = "예약자 학번", example = "00000000")
        String studentId;

        @Schema(description = "물품명", example = "방석")
        String itemName;

        @Schema(description = "물품 이미지")
        byte[] image;

        @Schema(description = "예약 개수", example = "2")
        Integer count;

        @Schema(description = "예약 시간", example = "2024-03-31T00:04:43.982361")
        LocalDateTime bookTime;

        @Schema(description = "요구 수령시간", example = "2024-04-01T17:30:00")
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
        @Schema(description = "토큰", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDAwMDAwMCIsImlhdCI6MTcxMTgxMDc2NSwiZXhwIjoyMDcxODEwNzY1fQ.2gbH5s0ODmTE59NRrFi9Fd8kqahHsfQqgHu6NQjjte1_4abMHmI6VfSKVI46SjftueKXSDFVr8WATiuf1ZMNzg")
        String token;
        @Schema(description = "물품번호", example = "1")
        long itemId;
        @Schema(description = "대여 예약 개수", example = "2")
        int count;
    }

    @Data
    public static class CancelRequestDTO {
        @Schema(description = "토큰", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDAwMDAwMCIsImlhdCI6MTcxMTgxMDc2NSwiZXhwIjoyMDcxODEwNzY1fQ.2gbH5s0ODmTE59NRrFi9Fd8kqahHsfQqgHu6NQjjte1_4abMHmI6VfSKVI46SjftueKXSDFVr8WATiuf1ZMNzg")
        String token;

        @Schema(description = "대여번호", example = "1")
        long itemRentId;
    }

    @Data
    @AllArgsConstructor
//    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class RestItemListDTO{
        @Schema(description = "물품번호", example = "1")
        Long id;

        @Schema(description = "물품명", example = "방석")
        String name;

        @Schema(description = "총개수", example = "15")
        Integer count;

        @Schema(description = "물품 이미지")
        byte[] image;

        @Schema(description = "대여중인 물품 개수", example = "7")
        Integer rentingCount;

        @Schema(description = "예약중인 물품 개수", example = "2")
        Long bookingCount;
    }

    @Data
    public static class RequestItemRent{
        Long itemRentId;
    }

}
