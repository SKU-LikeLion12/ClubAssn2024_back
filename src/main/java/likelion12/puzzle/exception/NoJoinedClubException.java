package likelion12.puzzle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NoJoinedClubException extends MessageException {

    public NoJoinedClubException(String message, HttpStatus status) {
        super(message, status);
    }
}
