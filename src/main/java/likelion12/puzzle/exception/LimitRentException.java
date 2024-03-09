package likelion12.puzzle.exception;

public class LimitRentException extends RuntimeException{
    public LimitRentException(String message) {
        super(message);
    }

    public LimitRentException(){super();}

}
