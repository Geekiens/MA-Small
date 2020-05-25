package bookReviewer.business.useCase.command.checkUserPromotionCommand;

import bookReviewer.business.boundary.in.useCase.command.CheckUserPromotionCommand;
import bookReviewer.business.mapper.RoleMapper;
import bookReviewer.business.model.Role;
import bookReviewer.persistence.model.Activity;
import bookReviewer.persistence.model.User;
import bookReviewer.persistence.repository.ActivityRepository;
import bookReviewer.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("CheckUserPromotionCommandImpl")
public class CheckUserPromotionCommandImpl implements CheckUserPromotionCommand {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    public void checkForUserPromotions(){
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            if (RoleMapper.roleBusiness(user.getRole()) == Role.ADMIN || RoleMapper.roleBusiness(user.getRole()) == Role.MODERATOR) {
                return;
            }
            List<Activity> activities = activityRepository.findAllByUser(user);
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
                userRepository.save(user);
            }

        });
    }
}
