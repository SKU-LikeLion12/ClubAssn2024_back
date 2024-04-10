package likelion12.puzzle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CustomEventException extends MessageException {

    public CustomEventException(String message, HttpStatus status) {
        super(message, status);
    }
}
