package likelion12.puzzle.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import likelion12.puzzle.security.JwtUtility;
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
    private final JwtUtility jwtUtility;

    @Operation(summary = "물품 대여창 목록 출력용", description = "대여중, 예약중 개수를 포함하여 물품의 목록을 조회", tags={"item-rent"})
    @GetMapping("/item-rent/list")
    public ResponseEntity<List<RestItemListDTO>> restItemList() {
        return ResponseEntity.ok(itemRentService.getrestItemList());
    }

    @Operation(summary = "물품 대여 예약", description = "토큰, 물품번호, 대여시도 개수 필요", tags={"item-rent"},
            responses = {@ApiResponse(responseCode="200", description="대여 성공"),
                    @ApiResponse(responseCode = "403", description = "대여 실패")
            })
    @PostMapping("/item-rent")
    public ResponseEntity<BookDTO> bookRequest(HttpServletRequest header, @RequestBody BookRequestDTO request){
        BookDTO bookDTO = itemRentService.bookItem(jwtUtility.getStudentId(header.getHeader("Authorization")), request.getItemId(), request.getCount());
        return ResponseEntity.status(HttpStatus.OK).body(bookDTO);
    }

    @Operation(summary = "물품 대여 예약 취소", description = "토큰, 대여번호 필요", tags={"item-rent"},
            responses = {@ApiResponse(responseCode="200", description="예약 취소 성공"),
            })
    @DeleteMapping("/item-rent")
    public ResponseEntity<?> cancelItem(HttpServletRequest header, @RequestBody CancelRequestDTO request){
        itemRentService.cancelRent(jwtUtility.getStudentId(header.getHeader("Authorization")),request.getItemRentId());
        return ResponseEntity.ok().build();
    }



}
