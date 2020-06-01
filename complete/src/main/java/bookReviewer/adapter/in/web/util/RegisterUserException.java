package bookReviewer.adapter.in.web.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RegisterUserException extends RuntimeException{
    public RegisterUserException() {
        super("Something went wrong while register user");
    }
}