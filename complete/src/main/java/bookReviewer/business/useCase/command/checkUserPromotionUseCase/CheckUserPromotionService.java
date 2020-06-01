package bookReviewer.business.useCase.command.checkUserPromotionUseCase;

import bookReviewer.business.boundary.in.useCase.command.CheckUserPromotionUseCase;
import bookReviewer.business.boundary.out.persistence.FindAllActivitiesByUser;
import bookReviewer.business.boundary.out.persistence.FindAllUsers;
import bookReviewer.business.boundary.out.persistence.SaveUser;
import bookReviewer.entity.user.Role;
import bookReviewer.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("CheckUserPromotionService")
public class CheckUserPromotionService implements CheckUserPromotionUseCase {

    @Autowired
    @Qualifier("FindAllUsersService")
    FindAllUsers findAllUsers;

    @Autowired
    @Qualifier("SaveUserService")
    SaveUser saveUser;

    @Autowired
    @Qualifier("FindAllActivitiesByUserService")
    FindAllActivitiesByUser findAllActivitiesByUser;

    public void checkForUserPromotions(){
        List<User> users = findAllUsers.findAllUsers();
        users.forEach(user -> {
            if (user.getRole() == Role.ADMIN || user.getRole() == Role.MODERATOR) {
                return;
            }
            Integer activityScore = user.getActivities().stream().mapToInt(activity -> {
                        switch (activity.getActivityType()) {
                            case BOOK_CREATED:
                                return 10;
                            case RATING_CREATED:
                                return 3;
                            case RATING_CREATED_WITH_COMMENT:
                                return 5;
                            case BOOK_DELETED_BY_ADMIN:
                                return -15;
                            case RATING_DELETED_BY_ADMIN:
                                return -20;
                            case RATING_DELETED_BY_MODERATOR:
                                return -10;
                            default: return 0;
                        }
                    }
            ).sum();
            System.out.println("Score: " + activityScore);
            if (activityScore != null && activityScore >= 50) {
                user.setRole(Role.MODERATOR);
                saveUser.saveUser(user);
            }
        });
    }
}
