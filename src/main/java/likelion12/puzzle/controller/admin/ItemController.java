package likelion12.puzzle.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion12.puzzle.DTO.ItemDTO;
import likelion12.puzzle.DTO.ItemDTO.ItemCreateResponse;
import likelion12.puzzle.domain.Item;
import likelion12.puzzle.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/admin/item")
@Tag(name = "관리자 페이지: 물품대여 관리 관련")
public class ItemController {

    private final ItemService itemService;

    // 물품 추가
    @Operation(summary = "관리자가 대여 물품 추가하는 API", description = "물품명, 물품 개수, 물품 이미지 삽입")
    @PostMapping("") //, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addItem(@RequestParam String name,
                                             @RequestParam int count,
                                             @RequestParam MultipartFile image)  throws IOException {

        Item item = itemService.save(name, count, image);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 물품 수정(이미지 안바꾸고 싶으면 안넣으면 됨)
    @Operation(summary = "관리자가 대여 물품 수정하는 API",
            description = "물품id, 수정하고자하는 이름, 사진 입력. 넣지 않은 항목은 원래 값으로 들어감. 개수는 입력해줘야함")
    @PutMapping("/{itemId}")
    public ResponseEntity<ItemCreateResponse> changeItem(@PathVariable Long itemId,
                                                         @RequestParam String name,
                                                         @RequestParam int count,
                                                         @RequestParam(required = false) MultipartFile image)  throws IOException  {

        Item item = itemService.changeItem(itemId, name, count, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ItemCreateResponse(item.getName(), item.getCount(), item.arrayToImage()));
    }

    @Operation(summary = "물품 1개 정보 확인하는 API", description = "물품의 id입력")
    @GetMapping("/{itemId}")
    public ItemCreateResponse findOneItem(@PathVariable("itemId") Long itemId) {
        Item item = itemService.findById(itemId);
        return new ItemCreateResponse(item.getName(), item.getCount(), item.arrayToImage());
    }

    @Operation(summary = "관리자가 대여 물품 삭제하는 API", description = "물품의 id입력")
    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.delete(itemId);
    }


    @Operation(summary = "관리자가 모든 대여 물품 조회하는 API (이미지 제외)", description = "이미지를 제외한 물품 정보 반환")
    @GetMapping("/all")
    public List<ItemDTO.ItemAllRequestExceptImage> findAllItemsExceptImage() {
        return itemService.findAllExceptImage(); // DTO로 쿼리 생성하기. hellospring => findUserAll() 참고
    }
}
