package likelion12.puzzle.controller;

import likelion12.puzzle.exception.HavePenaltyException;
import likelion12.puzzle.exception.LimitRentException;
import likelion12.puzzle.exception.NotEnoughItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(HavePenaltyException.class)
    public ResponseEntity<String> havePenalty(Exception e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("반납 지연으로 인해 대여가 제한됩니다.");
    }

    @ExceptionHandler(LimitRentException.class)
    public ResponseEntity<String> limitRent(Exception e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(NotEnoughItemException.class)
    public ResponseEntity<String> notEnoughItem(Exception e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("물품의 잔여 개수가 부족합니다.");
    }


}
