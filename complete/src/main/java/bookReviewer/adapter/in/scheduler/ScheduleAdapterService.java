package bookReviewer.adapter.in.scheduler;


import bookReviewer.business.boundary.in.useCase.command.CheckUserPromotionUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ScheduleAdapterService")
public class ScheduleAdapterService implements ScheduleAdapter{

    @Autowired
    @Qualifier("CheckUserPromotionService")
    CheckUserPromotionUseCase checkUserPromotionUseCase;

    public void checkForUserPromotions(){
        checkUserPromotionUseCase.checkForUserPromotions();
    }
}
