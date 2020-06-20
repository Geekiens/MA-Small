package bookReviewer.presentation.controller;

import bookReviewer.business.service.UserService;
import bookReviewer.persistence.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService = new UserService();


    @PostMapping(path="/login", produces = "application/json")
    public String login(@RequestBody Map<String, String> credentials) throws Exception {
        return userService.loginUser(credentials.get("username"), credentials.get("password"));
    }

    @PostMapping(path="/register*", produces = "application/json")
    public void register(@RequestBody Map<String, String> credentials) throws Exception {

        if (credentials.get("gender").equals("F")){
            userService.registerUser(credentials.get("username"), credentials.get("password"), credentials.get("email"), Role.USER, 0);
        }
        else if (credentials.get("gender").equals("M")){
            userService.registerUser(credentials.get("username"), credentials.get("password"), credentials.get("email"), Role.USER, 1);
        }
        userService.registerUser(credentials.get("username"), credentials.get("password"), credentials.get("email"), Role.USER, -1);

    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e)
    {
        e.printStackTrace();
        System.err.println(e);
    }
}
