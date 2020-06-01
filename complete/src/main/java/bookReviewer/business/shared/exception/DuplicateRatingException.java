package bookReviewer.business.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateRatingException extends RuntimeException{
    public DuplicateRatingException() {
        super("Rating of this user already exists for this book");
    }
}
