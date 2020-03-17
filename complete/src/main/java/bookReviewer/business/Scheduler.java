package bookReviewer.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    UserService userService;

    @Scheduled(fixedRate = 30000)
    public void acquireModerators() {
        System.out.println("Aquire new mods");
        userService.checkForUserPromotions();
    }
}
