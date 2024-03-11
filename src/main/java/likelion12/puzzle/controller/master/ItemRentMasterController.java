package likelion12.puzzle.controller.master;

import likelion12.puzzle.DTO.ItemRentDTO.AdminBookListDTO;
import likelion12.puzzle.service.ItemRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemRentMasterController {

    private final ItemRentService itemRentService;

    @GetMapping("/master/item-rent/list")
    public ResponseEntity<List<AdminBookListDTO>> itemRentList(){
        List<AdminBookListDTO> adminBookList = itemRentService.allBookList();
        return ResponseEntity.ok().body(adminBookList);
    }



}
