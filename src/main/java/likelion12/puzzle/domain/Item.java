package likelion12.puzzle.domain;

import jakarta.persistence.*;
import likelion12.puzzle.service.ImageUtility;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Item {

    @Id @GeneratedValue @Column(name="item_id")
    Long id;

    @Setter
    String name;
    @Setter
    Integer count;
    Integer rentingCount;

    // 이미지를 담는 byte 배열을 BLOB(Binary Large Object) 형식으로 저장
    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB") // TINYBLOB: ~255Byte BLOB: ~64KB  MEDIUMBLOB: ~16MB LONGBLOB: ~4GB
    private byte[] image;

    public Item(String name, int count, byte[] image){
        this.name = name;
        this.count = count;
        this.image = image;
        this.rentingCount = 0;
    }

    public void rentItem(int num){
        rentingCount += num;
    }

    public void returnItem(int num){
        rentingCount -= num;
    }
    public String arrayToImage() {
        return ImageUtility.encodeImage(this.image);
    }

    public void setImage(MultipartFile image) throws IOException {
        this.image = image.getBytes();
    }

    @Getter
    public static class ItemAllRequest {
        private Long id;
        private String name;
        private int count;

        public ItemAllRequest(Long id, String name, int count) {
            this.id = id;
            this.name = name;
            this.count = count;
        }
    }

}
