package likelion12.puzzle.domain;

import jakarta.persistence.*;
import likelion12.puzzle.service.ImageUtility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Club {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;
    private String description;

    // 이미지를 담는 byte 배열을 BLOB(Binary Large Object) 형식으로 저장
    @Lob
    @Column(name = "logo", columnDefinition = "MEDIUMBLOB") // TINYBLOB: ~255Byte BLOB: ~64KB  MEDIUMBLOB: ~16MB LONGBLOB: ~4GB
    private byte[] logo;

    public Club(String clubName, String description,  byte[] logo) {
        this.name = clubName;
        this.description = description;
        this.logo = logo;
    }

    public String arrayToImage() {
        return ImageUtility.encodeImage(this.logo);
    }
}