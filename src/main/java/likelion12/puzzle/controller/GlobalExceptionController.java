package likelion12.puzzle.controller;

import likelion12.puzzle.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(HavePenaltyException.class)
    public ResponseEntity<String> havePenalty(Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("반납 지연으로 인해 대여가 제한됩니다.");
    }

    @ExceptionHandler(LimitRentException.class)
    public ResponseEntity<String> limitRent(Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(NotEnoughItemException.class)
    public ResponseEntity<String> notEnoughItem(Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("물품의 잔여 개수가 부족합니다.");
    }

    @ExceptionHandler(MemberExistException.class)
    public ResponseEntity<String> alreadyExistMember(MemberExistException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    @ExceptionHandler(MemberLoginException.class)
    public ResponseEntity<String> handleMemberLoginException(MemberLoginException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    @ExceptionHandler(NotExistJoinEventException.class)
    public ResponseEntity<String> handleJoinEventException(NotExistJoinEventException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    @ExceptionHandler(NotExistJoinClubException.class)
    public ResponseEntity<String> handleJoinEventException(NotExistJoinClubException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    @ExceptionHandler(CustomEventException.class)
    public ResponseEntity<String> handleEventException(CustomEventException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
}