package bookReviewer.presentation;

import bookReviewer.business.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService = new UserService();


    @GetMapping(path="/user", produces = "application/json")
    public String getRatingsByBookId() {
        return userService.getToken();
    }
}
