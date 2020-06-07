package bookReviewer.periphery.persistence.service;

import bookReviewer.adapter.out.persistence.model.Activity;
import bookReviewer.adapter.out.persistence.model.User;
import bookReviewer.adapter.out.persistence.repository.ActivityRepository;
import bookReviewer.periphery.persistence.repository.ActivityRepositoryJpa;

import java.util.List;

public class ActivityRepositoryService implements ActivityRepository {

    ActivityRepositoryJpa activityRepositoryJpa;

    public ActivityRepositoryService(ActivityRepositoryJpa activityRepositoryJpa){
        this.activityRepositoryJpa = activityRepositoryJpa;
    }

    public List<Activity> findAllByUser(User user){
        return activityRepositoryJpa.findAllByUser(user);
    }
    public void save(Activity activity){
        activityRepositoryJpa.save(activity);
    }
}
