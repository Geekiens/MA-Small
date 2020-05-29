package bookReviewer.business.useCase.command.checkUserPromotionUseCase;

import bookReviewer.business.boundary.in.useCase.command.CheckUserPromotionUseCase;
import bookReviewer.business.boundary.out.persistence.FindAllActivitiesByUser;
import bookReviewer.business.boundary.out.persistence.FindAllUsers;
import bookReviewer.business.boundary.out.persistence.FindBookById;
import bookReviewer.business.boundary.out.persistence.SaveUser;
import bookReviewer.business.mapper.RoleMapper;
import bookReviewer.business.shared.Role;
import bookReviewer.persistence.model.Activity;
import bookReviewer.persistence.model.User;
import bookReviewer.persistence.repository.ActivityRepository;
import bookReviewer.persistence.repository.UserRepository;
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
            if (RoleMapper.roleBusiness(user.getRole()) == Role.ADMIN || RoleMapper.roleBusiness(user.getRole()) == Role.MODERATOR) {
                return;
            }
            List<Activity> activities = findAllActivitiesByUser.findAllActivitiesByUser(user);
            Integer activityScore = activities.stream().mapToInt(activity -> {
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
                user.setRole(RoleMapper.role(Role.MODERATOR));
                saveUser.saveUser(user);
            }

        });
    }
}
