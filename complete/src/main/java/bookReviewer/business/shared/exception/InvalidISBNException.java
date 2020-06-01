package bookReviewer.business.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidISBNException extends RuntimeException{

    public InvalidISBNException() {
        super("Book has invalid ISBN");
    }
}
