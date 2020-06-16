package bookReviewer.adapter.in.web.util;

public class InvalidTokenException extends RuntimeException{
        public InvalidTokenException() {
            super("No Valid Token Provided");
        }
}
