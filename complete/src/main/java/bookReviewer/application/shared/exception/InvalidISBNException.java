package bookReviewer.application.shared.exception;

public class InvalidISBNException extends RuntimeException{

    public InvalidISBNException() {
        super("Book has invalid ISBN");
    }
}
