package likelion12.puzzle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomEventException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public CustomEventException(String s, HttpStatus httpStatus) {
        super(s);
        this.status = httpStatus;
        this.message = s;
    }
}
