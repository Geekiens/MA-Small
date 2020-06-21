package bookReviewer.adapter.in.web.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidTokenException extends RuntimeException{
        public InvalidTokenException() {
            super("No Valid Token Provided");
        }
}