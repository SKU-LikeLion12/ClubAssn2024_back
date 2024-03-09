package likelion12.puzzle.controller;

import likelion12.puzzle.service.ItemRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static likelion12.puzzle.DTO.ItemRentDTO.*;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/item-rent")
public class ItemRentController {

    private final ItemRentService itemRentService;

    @GetMapping("/item-rent/list")
    public ResponseEntity<List<RestItemListDTO>> restItemList(){
        return ResponseEntity.ok(itemRentService.getrestItemList());
    }

    @PostMapping("/item-rent")
    public ResponseEntity<BookDTO> bookRequest(@RequestBody BookRequestDTO request){
        BookDTO bookDTO = itemRentService.bookItem(request.getStudentId(), request.getItemId(), request.getCount());
        return ResponseEntity.status(HttpStatus.OK).body(bookDTO);
    }

}
