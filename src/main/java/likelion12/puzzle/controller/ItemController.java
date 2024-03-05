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
@CrossOrigin(origins = "http://127.0.0.1:3000")
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
        String base64Image = ImageUtility.encodeImage(item.getImage());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ItemCreateRequest(item.getName(), item.getCount(), base64Image));
    }


    @ResponseBody
    @GetMapping("/item/{itemId}")
    public ItemCreateRequest findOneItem(@PathVariable("itemId") Long itemId) {
        Item item = itemService.findById(itemId);
        // 데이터베이스에 저장된 이미지(byte 배열) => encoder로 다시 프론트에 뿌려주기(이미지 그 자체)
        String base64Image = ImageUtility.encodeImage(item.getImage());
        return new ItemCreateRequest(item.getName(), item.getCount(), base64Image);
    }

    @DeleteMapping("/item/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.delete(itemId);
    }

    @ResponseBody
    @GetMapping("/items")
    public List<ItemAllRequest> findAllItems() {
        List<Item> items = itemService.findAll();
        List<ItemAllRequest> itemDTOS = new ArrayList<>();

        for (Item item : items) {
            ItemAllRequest dto = new ItemAllRequest(item.getId(), item.getName(), item.getCount());
            itemDTOS.add(dto);
        }

        return itemDTOS;
    }
}
