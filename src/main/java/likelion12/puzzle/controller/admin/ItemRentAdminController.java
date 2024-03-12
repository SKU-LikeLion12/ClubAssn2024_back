package likelion12.puzzle.controller.admin;

import likelion12.puzzle.DTO.ItemRentDTO;
import likelion12.puzzle.DTO.ItemRentDTO.AdminBookListDTO;
import likelion12.puzzle.DTO.ItemRentDTO.AdminRentListDTO;
import likelion12.puzzle.DTO.ItemRentDTO.RequestItemRent;
import likelion12.puzzle.service.ItemRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemRentAdminController {

    private final ItemRentService itemRentService;

    @GetMapping("/admin/item-rent/book-list")
    public ResponseEntity<List<AdminBookListDTO>> itemBookList(){
        List<AdminBookListDTO> adminBookList = itemRentService.allBookList();
        return ResponseEntity.ok().body(adminBookList);
    }

    @GetMapping("/admin/item-rent/rent-list")
    public ResponseEntity<List<AdminRentListDTO>> itemRentList(){
        List<AdminRentListDTO> adminRentList = itemRentService.allRentList();
        return ResponseEntity.ok().body(adminRentList);
    }

    @PostMapping("/admin/item-rent/receive")
    public ResponseEntity<?> itemReceive(RequestItemRent request){
        itemRentService.receiveItem(request.getItemRentId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/item-rent/return")
    public ResponseEntity<?> itemReturn(RequestItemRent request){
        itemRentService.returnItem(request.getItemRentId());
        return ResponseEntity.ok().build();
    }



}
