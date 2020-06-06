package bookReviewer.application.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenInteractionException extends RuntimeException{
        public ForbiddenInteractionException() {
            super("Role is not sufficient for this action");
        }
}
