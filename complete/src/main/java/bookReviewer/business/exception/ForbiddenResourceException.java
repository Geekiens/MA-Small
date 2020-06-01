package bookReviewer.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenResourceException extends RuntimeException{
        public ForbiddenResourceException() {
            super("Resource can't be edited by thus user.");
        }
}
