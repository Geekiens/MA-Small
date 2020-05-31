package bookReviewer.presentation.controller;

import bookReviewer.business.boundary.in.useCase.command.RegisterUserUseCase;
import bookReviewer.business.boundary.in.useCase.query.GetTokenByLoginUseCase;
import bookReviewer.business.shared.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    @Qualifier("RegisterUserService")
    RegisterUserUseCase registerUserUseCase;

    @Autowired
    @Qualifier("GetTokenByLoginService")
    GetTokenByLoginUseCase getTokenByLoginUseCase;


    @PostMapping(path="/login", produces = "application/json")
    public String login(@RequestBody Map<String, String> credentials) throws Exception {
        return getTokenByLoginUseCase.loginUser(credentials.get("username"), credentials.get("password"));
    }

    @PostMapping(path="/register*", produces = "application/json")
    public void register(@RequestBody Map<String, String> credentials) throws Exception {
        registerUserUseCase.registerUser(credentials.get("username"), credentials.get("password"), credentials.get("email"), Role.USER);
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e)
    {
        e.printStackTrace();
        System.err.println(e);
    }


}
