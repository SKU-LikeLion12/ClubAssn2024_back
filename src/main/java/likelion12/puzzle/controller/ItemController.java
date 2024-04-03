package likelion12.puzzle.controller;

import io.swagger.v3.oas.annotations.Operation;
import likelion12.puzzle.DTO.ItemDTO;
import likelion12.puzzle.DTO.ItemDTO.ItemCreateRequest;
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
public class ItemController {

    private final ItemService itemService;

    // 물품 추가
    @ResponseBody
    @Operation(summary = "", description = "", tags={""})
    @PostMapping("/item") //, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Item> addItem(@RequestParam String name,
                                             @RequestParam int count,
                                             @RequestParam MultipartFile image)  throws IOException {

        Item item = itemService.save(name, count, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    // 물품 수정(이미지 안바꾸고 싶으면 안넣으면 됨)
    @Operation(summary = "", description = "", tags={""})
    @PutMapping("/item/{itemId}")
    public ResponseEntity<ItemCreateRequest> changeItem(@PathVariable Long itemId,
                                           @RequestParam String name,
                                           @RequestParam int count,
                                           @RequestParam(required = false) MultipartFile image)  throws IOException  {

        Item item = itemService.changeItem(itemId, name, count, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ItemCreateRequest(item.getName(), item.getCount(), item.arrayToImage()));
    }

    @ResponseBody
    @Operation(summary = "", description = "", tags={""})
    @GetMapping("/item/{itemId}")
    public ItemCreateRequest findOneItem(@PathVariable("itemId") Long itemId) {
        Item item = itemService.findById(itemId);
        return new ItemCreateRequest(item.getName(), item.getCount(), item.arrayToImage());
    }

    @Operation(summary = "", description = "", tags={""})
    @DeleteMapping("/item/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.delete(itemId);
    }


    @ResponseBody
    @Operation(summary = "", description = "", tags={""})
    @GetMapping("/items")
    public List<ItemDTO.ItemAllRequestExceptImage> findAllItemsExceptImage() {
        return itemService.findAllExceptImage(); // DTO로 쿼리 생성하기. hellospring => findUserAll() 참고
    }
}
