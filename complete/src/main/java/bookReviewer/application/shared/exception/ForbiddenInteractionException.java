package bookReviewer.application.shared.exception;


public class ForbiddenInteractionException extends RuntimeException{
        public ForbiddenInteractionException() {
            super("Role is not sufficient for this action");
        }
}
