package bookReviewer.application.shared.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String exception) {
        super(exception);
    }
}
