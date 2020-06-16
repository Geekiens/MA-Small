package bookReviewer.application.shared.exception;

public class DuplicateRatingException extends RuntimeException{
    public DuplicateRatingException() {
        super("Rating of this user already exists for this book");
    }
}
