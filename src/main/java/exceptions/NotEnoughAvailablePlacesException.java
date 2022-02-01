package exceptions;

public class NotEnoughAvailablePlacesException extends RuntimeException {

    public NotEnoughAvailablePlacesException(String message) {
        super(message);
    }
}
