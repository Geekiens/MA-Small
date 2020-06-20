package bookReviewer.application.useCase.command.checkUserPromotionUseCase;

import bookReviewer.application.boundary.in.useCase.command.CheckUserPromotionUseCase;
import bookReviewer.application.boundary.out.persistence.FindAllActivitiesByUser;
import bookReviewer.application.boundary.out.persistence.FindAllUsers;
import bookReviewer.application.boundary.out.persistence.SaveUser;
import bookReviewer.entity.user.Role;
import bookReviewer.entity.user.User;

import java.util.List;


public class CheckUserPromotionService implements CheckUserPromotionUseCase {

    FindAllUsers findAllUsers;

    SaveUser saveUser;

    FindAllActivitiesByUser findAllActivitiesByUser;

    public CheckUserPromotionService(FindAllUsers findAllUsers,
                                     SaveUser saveUser,
                                     FindAllActivitiesByUser findAllActivitiesByUser){
        this.findAllUsers = findAllUsers;
        this.saveUser = saveUser;
        this.findAllActivitiesByUser = findAllActivitiesByUser;
    }

    public void checkForUserPromotions(){
        List<User> users = findAllUsers.findAllUsers();
        users.forEach(user -> {
            if (user.getRole() == Role.ADMIN || user.getRole() == Role.MODERATOR) {
                return;
            }
            int activityScore = user.calculateActivityPoints();
            System.out.println("Score: " + activityScore);
            if (activityScore >= 50 ||
                    (activityScore >= 40 && user.getGender().equals("F"))) {
                user.setRole(Role.MODERATOR);
                saveUser.saveUser(user);
            }
        });
    }
}
