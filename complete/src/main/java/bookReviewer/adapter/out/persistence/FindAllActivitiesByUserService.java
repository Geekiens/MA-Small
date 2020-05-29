package bookReviewer.adapter.out.persistence;

import bookReviewer.business.boundary.out.persistence.FindAllActivitiesByUser;
import bookReviewer.persistence.model.Activity;
import bookReviewer.persistence.model.User;
import bookReviewer.persistence.repository.ActivityRepository;

import java.util.List;

public class FindAllActivitiesByUserService implements FindAllActivitiesByUser {
    ActivityRepository activityRepository;
    public List<Activity> findAllUsers(User user){
        return activityRepository.findAllByUser(user);
    }
}
