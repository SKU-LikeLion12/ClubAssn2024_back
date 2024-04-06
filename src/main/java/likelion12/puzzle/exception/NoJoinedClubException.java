package likelion12.puzzle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NoJoinedClubException extends RuntimeException {
    String message;
    HttpStatus status;

    public NoJoinedClubException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
