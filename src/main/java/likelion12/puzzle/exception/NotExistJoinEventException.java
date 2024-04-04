package likelion12.puzzle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotExistJoinEventException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public NotExistJoinEventException(String s, HttpStatus httpStatus) {
        super(s);
        this.message = s;
        this.status = httpStatus;
    }
}
