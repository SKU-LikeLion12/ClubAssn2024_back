package likelion12.puzzle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotExistJoinClubException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public NotExistJoinClubException(String s, HttpStatus httpStatus) {
        super(s);
        this.message = s;
        this.status = httpStatus;
    }
}
