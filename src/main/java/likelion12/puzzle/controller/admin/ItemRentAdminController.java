package likelion12.puzzle.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion12.puzzle.DTO.ItemRentDTO.AdminBookListDTO;
import likelion12.puzzle.DTO.ItemRentDTO.AdminRentListDTO;
import likelion12.puzzle.DTO.ItemRentDTO.RequestItemRent;
import likelion12.puzzle.service.ItemRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/item-rent")
@Tag(name = "관리자 페이지: 물품대여 관리 관련")
public class ItemRentAdminController {

    private final ItemRentService itemRentService;

    @Operation(summary = "(민지) 물품 예약 리스트", description = "헤더에 관리자 토큰 필요")
    @GetMapping("/book-list")
    public ResponseEntity<List<AdminBookListDTO>> itemBookList(){
        List<AdminBookListDTO> adminBookList = itemRentService.allBookList();
        return ResponseEntity.ok().body(adminBookList);
    }

    @Operation(summary = "(민지) 물품 대여중 리스트", description = "헤더에 관리자 토큰 필요")
    @GetMapping("/rent-list")
    public ResponseEntity<List<AdminRentListDTO>> itemRentList(){
        List<AdminRentListDTO> adminRentList = itemRentService.allRentList();
        return ResponseEntity.ok().body(adminRentList);
    }

    @Operation(summary = "(민지) 물품 수령", description = "헤더에 관리자 토큰 필요")
    @PostMapping("")
    public ResponseEntity<?> itemReceive(@RequestBody RequestItemRent request){
        itemRentService.receiveItem(request.getItemRentId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "(민지) 물품 반환", description = "헤더에 관리자 토큰 필요")
    @PutMapping("")
    public ResponseEntity<?> itemReturn(@RequestBody RequestItemRent request){
        itemRentService.returnItem(request.getItemRentId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "(민지) 예약 취소", description = "헤더에 관리자 토큰 필요")
    @DeleteMapping("")
    public ResponseEntity<?> cancelRent(@RequestBody RequestItemRent request){
        itemRentService.adminCancelRent(request.getItemRentId());
        return ResponseEntity.ok().build();
    }

}
