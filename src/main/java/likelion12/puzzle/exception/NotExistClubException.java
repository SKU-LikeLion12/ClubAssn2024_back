package likelion12.puzzle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotExistClubException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public NotExistClubException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.status = httpStatus;
    }
}
