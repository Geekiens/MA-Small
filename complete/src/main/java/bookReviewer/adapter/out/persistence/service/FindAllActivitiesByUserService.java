package bookReviewer.adapter.out.persistence.service;

import bookReviewer.business.boundary.out.persistence.FindAllActivitiesByUser;
import bookReviewer.persistence.model.Activity;
import bookReviewer.persistence.model.User;
import bookReviewer.persistence.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("FindAllActivitiesByUserService")
public class FindAllActivitiesByUserService implements FindAllActivitiesByUser {
    @Autowired
    ActivityRepository activityRepository;
    public List<Activity> findAllActivitiesByUser(User user){
        return activityRepository.findAllByUser(user);
    }
}
