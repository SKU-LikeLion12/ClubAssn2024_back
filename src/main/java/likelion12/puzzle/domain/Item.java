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

    String name;
    Integer count;

    // 이미지를 담는 byte 배열을 BLOB(Binary Large Object) 형식으로 저장
    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB") // TINYBLOB: ~255Byte BLOB: ~64KB  MEDIUMBLOB: ~16MB LONGBLOB: ~4GB
    private byte[] image;

    public Item(String name, int count, byte[] image){
        this.name = name;
        this.count = count;
        this.image = image;
    }

    public void changeItem(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String arrayToImage() {
        return ImageUtility.encodeImage(this.image);
    }

    public void setImage(MultipartFile image) throws IOException {
        this.image = image.getBytes();
    }
}
