package likelion12.puzzle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberExistException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public MemberExistException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}