package bookReviewer.presentation.controller;

import bookReviewer.business.boundary.in.useCase.command.RegisterUserCommand;
import bookReviewer.business.boundary.in.useCase.query.GetTokenByLoginQuery;
import bookReviewer.business.service.UserService;
import bookReviewer.business.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    @Qualifier("UserService")
    RegisterUserCommand registerUserCommand;

    @Autowired
    @Qualifier("UserService")
    GetTokenByLoginQuery getTokenByLoginQuery;


    @PostMapping(path="/login", produces = "application/json")
    public String login(@RequestBody Map<String, String> credentials) throws Exception {
        return getTokenByLoginQuery.loginUser(credentials.get("username"), credentials.get("password"));
    }

    @PostMapping(path="/register*", produces = "application/json")
    public void register(@RequestBody Map<String, String> credentials) throws Exception {
        registerUserCommand.registerUser(credentials.get("username"), credentials.get("password"), credentials.get("email"), Role.USER);
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e)
    {
        e.printStackTrace();
        System.err.println(e);
    }


}
