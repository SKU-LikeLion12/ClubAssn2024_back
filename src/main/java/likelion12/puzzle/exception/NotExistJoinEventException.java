package likelion12.puzzle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NotExistJoinEventException extends MessageException {

    public NotExistJoinEventException(String message, HttpStatus status) {
        super(message, status);
    }
}
