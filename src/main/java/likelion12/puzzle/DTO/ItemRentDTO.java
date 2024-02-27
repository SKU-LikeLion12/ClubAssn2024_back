package likelion12.puzzle.DTO;

import lombok.Getter;

public class ItemRentDTO {

    @Getter
    public static class ItemRentSummary {
        private Long totalCount;
        private Long uniqueItemCount;

        public ItemRentSummary(Long totalCount, Long uniqueItemCount) {
            this.totalCount = totalCount;
            this.uniqueItemCount = uniqueItemCount;
        }
    }
}
