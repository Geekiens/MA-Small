package bookReviewer.adapter.in.scheduler;

import bookReviewer.application.boundary.in.useCase.command.CheckUserPromotionUseCase;

public class ScheduleAdapterService implements ScheduleAdapter{

    CheckUserPromotionUseCase checkUserPromotionUseCase;

    public ScheduleAdapterService(CheckUserPromotionUseCase checkUserPromotionUseCase){
        this.checkUserPromotionUseCase = checkUserPromotionUseCase;
    }

    public void checkForUserPromotions(){
        checkUserPromotionUseCase.checkForUserPromotions();
    }
}
