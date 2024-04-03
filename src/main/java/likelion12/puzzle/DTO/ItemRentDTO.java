package likelion12.puzzle.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import likelion12.puzzle.domain.ItemRent;
import likelion12.puzzle.service.ImageUtility;
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
        String image;

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
            this.image = image;
            this.count = itemRent.getCount();
            this.bookTime = itemRent.getOfferDate();
            this.needReceiveTime = needReceiveTime;
        }
    }

    @Data
    public static class RentDTO {
        @Schema(description = "물품대여번호", example = "1")
        Long itemRentId;

        @Schema(description = "학번", example = "00000000")
        String studentId;

        @Schema(description = "물품이름", example = "방석")
        String itemName;

        @Schema(description = "물품 이미지", example = "")
        String image;

        @Schema(description = "물품 대여 개수", example = "2")
        Integer count;

        @Schema(description = "대여 시간(수령시간)", example = "2024-03-31T00:04:43.982361")
        LocalDateTime rentTime;

        @Schema(description = "필요 반납 시간", example = "2024-04-07T00:04:43.982361")
        LocalDateTime needReturnTime;

        @Schema(description = "지연여부", example = "NO_DELAY or DELAY or LONG_DELAY")
        DelayStatus state;

        public RentDTO(ItemRent itemRent, LocalDateTime needReturnTime, DelayStatus state){
            this.itemRentId = itemRent.getId();
            this.studentId = itemRent.getRenter().getStudentId();
            this.itemName = itemRent.getItem().getName();
            this.image = ImageUtility.encodeImage(itemRent.getItem().getImage());
            this.count = itemRent.getCount();
            this.rentTime = itemRent.getReceiveDate();
            this.needReturnTime = needReturnTime;
            this.state = state;
        }
    }

    @Data
    @AllArgsConstructor
    public static class AdminBookListDTO{

        @Schema(description = "물품 예약번호", example = "1")
        Long itemRentId;

        @Schema(description = "대여자 학번", example = "00000000")
        String studentId;

        @Schema(description = "대여지 이름", example = "홍길동")
        String name;

        @Schema(description = "물품 이름", example = "방석")
        String itemName;

        @Schema(description = "예약자의 대표동아리 이름", example = "멋쟁이사자처럼")
        String iconClub;

        @Schema(description = "예약개수", example = "2")
        Integer count;

        @Schema(description = "예약 시간", example = "2024-03-31T00:04:43.982361")
        LocalDateTime bookTime;
    }

    @Data
    public static class AdminRentListDTO{

        @Schema(description = "물품 대여번호", example = "1")
        Long itemRentId;

        @Schema(description = "대여자 학번", example = "00000000")
        String studentId;

        @Schema(description = "대여지 이름", example = "홍길동")
        String name;

        @Schema(description = "물품명", example = "방석")
        String itemName;

        @Schema(description = "대여자의 대표동아리 이름", example = "멋쟁이사자처럼")
        String iconClub;

        @Schema(description = "대여 개수", example = "2")
        Integer count;

        @Schema(description = "대여 시간(수령시간)", example = "2024-03-31T00:04:43.982361")
        LocalDateTime rentTime;

        @Schema(description = "지연여부", example = "NO_DELAY or DELAY or LONG_DELAY")
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
        @Schema(description = "물품대여번호", example = "1")
        ItemRent itemRent;

        @Schema(description = "필요반납일", example = "2024-04-07T00:04:43.982361")
        LocalDateTime needReturnTime;

        public ReceiveDTO(ItemRent itemRent, LocalDateTime needReturnTime) {
            this.itemRent = itemRent;
            this.needReturnTime = needReturnTime;
        }
    }

    @Data
    public static class BookRequestDTO{
//        @Schema(description = "토큰", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDAwMDAwMCIsImlhdCI6MTcxMTgxMDc2NSwiZXhwIjoyMDcxODEwNzY1fQ.2gbH5s0ODmTE59NRrFi9Fd8kqahHsfQqgHu6NQjjte1_4abMHmI6VfSKVI46SjftueKXSDFVr8WATiuf1ZMNzg")
//        String token;
        @Schema(description = "물품번호", example = "1")
        long itemId;
        @Schema(description = "대여 예약 개수", example = "2")
        int count;
    }

    @Data
    public static class CancelRequestDTO {
//        @Schema(description = "토큰", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDAwMDAwMCIsImlhdCI6MTcxMTgxMDc2NSwiZXhwIjoyMDcxODEwNzY1fQ.2gbH5s0ODmTE59NRrFi9Fd8kqahHsfQqgHu6NQjjte1_4abMHmI6VfSKVI46SjftueKXSDFVr8WATiuf1ZMNzg")
//        String token;
        @Schema(description = "대여번호", example = "1")
        long itemRentId;
    }

    @Data
//    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class RestItemListDTO{
        @Schema(description = "물품번호", example = "1")
        Long id;

        @Schema(description = "물품명", example = "방석")
        String name;

        @Schema(description = "총개수", example = "15")
        Integer count;

        @Schema(description = "물품 이미지")
        String image;

        @Schema(description = "대여중인 물품 개수", example = "7")
        Integer rentingCount;

        @Schema(description = "예약중인 물품 개수", example = "2")
        Long bookingCount;

        public RestItemListDTO(Long id, String name, Integer count, byte[] image, Integer rentingCount, Long bookingCount) {
            this.id = id;
            this.name = name;
            this.count = count;
            this.image = ImageUtility.encodeImage(image);
            this.rentingCount = rentingCount;
            this.bookingCount = bookingCount;
        }
    }

    @Data
    public static class RequestItemRent{
        @Schema(description = "물품 예약번호", example = "1")
        Long itemRentId;
    }

}
