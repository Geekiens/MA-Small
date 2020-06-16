package bookReviewer.adapter.in.web.util;

public class RegisterUserException extends RuntimeException{
    public RegisterUserException() {
        super("Something went wrong while register user");
    }
}
