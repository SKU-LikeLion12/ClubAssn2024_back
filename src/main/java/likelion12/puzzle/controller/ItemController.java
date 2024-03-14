package likelion12.puzzle.controller;

import jakarta.websocket.server.PathParam;
import likelion12.puzzle.DTO.ItemDTO;
import likelion12.puzzle.domain.Item;
import likelion12.puzzle.service.ImageUtility;
import likelion12.puzzle.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import likelion12.puzzle.DTO.ItemDTO.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ItemController {

    private final ItemService itemService;

    // 물품 추가
    @ResponseBody
    @PostMapping("/item") //, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Item> addItem(@RequestParam String name,
                                             @RequestParam int count,
                                             @RequestParam MultipartFile image)  throws IOException {

        // 프론트에서 받은 이미지(이미지 그 자체) => getByte()함수로 byte배열로 변환
        byte[] imageBytes = image.getBytes();
        Item item = itemService.save(name, count, imageBytes);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    // 물품 수정(이미지 안바꾸고 싶으면 안넣으면 됨)
    @PutMapping("/item/{itemId}")
    public ResponseEntity<ItemCreateRequest> changeItem(@PathVariable Long itemId,
                                           @RequestParam String name,
                                           @RequestParam int count,
                                           @RequestParam(required = false) MultipartFile image)  throws IOException  {

        Item item = itemService.changeItem(itemId, name, count, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ItemCreateRequest(item.getName(), item.getCount(), item.arrayToImage()));
    }


    @ResponseBody
    @GetMapping("/item/{itemId}")
    public ItemCreateRequest findOneItem(@PathVariable("itemId") Long itemId) {
        Item item = itemService.findById(itemId);
        return new ItemCreateRequest(item.getName(), item.getCount(), item.arrayToImage());
    }

    @DeleteMapping("/item/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.delete(itemId);
    }

    @ResponseBody
    @GetMapping("/items")
    public List<Item.ItemAllRequest> findAllItems() {
        return itemService.findAllExceptImage(); // DTO로 쿼리 생성하기. hellospring => findUserAll() 참고
    }
}
