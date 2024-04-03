package likelion12.puzzle.controller;

import likelion12.puzzle.exception.HavePenaltyException;
import likelion12.puzzle.exception.LimitRentException;
import likelion12.puzzle.exception.NotEnoughItemException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(HavePenaltyException.class)
    public ResponseEntity<ErrorResponse> havePenalty(Exception e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("반납 지연으로 인해 대여가 제한됩니다."));
    }

    @ExceptionHandler(LimitRentException.class)
    public ResponseEntity<ErrorResponse> limitRent(Exception e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(NotEnoughItemException.class)
    public ResponseEntity<ErrorResponse> notEnoughItem(Exception e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("물품의 잔여 개수가 부족합니다."));
    }

    @Data
    @NoArgsConstructor
    public static class ErrorResponse{
        public ErrorResponse(String message) {
            this.message = message;
        }

        String message;
    }


}
