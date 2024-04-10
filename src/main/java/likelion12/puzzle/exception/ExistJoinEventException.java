package likelion12.puzzle.exception;

import org.springframework.http.HttpStatus;

public class ExistJoinEventException extends MessageException{
    public ExistJoinEventException(String message, HttpStatus status) {
        super(message, status);
    }
}
