package bookReviewer.business;

import bookReviewer.business.boundary.in.useCase.command.CheckUserPromotionUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    @Qualifier("CheckUserPromotionService")
    CheckUserPromotionUseCase checkUserPromotionUseCase;

    @Scheduled(fixedRate = 30000)
    public void acquireModerators() {
        System.out.println("Aquire new mods");
        checkUserPromotionUseCase.checkForUserPromotions();
    }
}
