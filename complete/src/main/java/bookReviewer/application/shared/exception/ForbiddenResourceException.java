package bookReviewer.application.shared.exception;

public class ForbiddenResourceException extends RuntimeException{
        public ForbiddenResourceException() {
            super("Resource can't be edited by thus user.");
        }
}
