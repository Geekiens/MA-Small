package bookReviewer.periphery.persistence.service;

import bookReviewer.adapter.out.persistence.model.Activity;
import bookReviewer.adapter.out.persistence.model.User;
import bookReviewer.adapter.out.persistence.repository.ActivityRepository;
import bookReviewer.periphery.persistence.repository.ActivityRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("ActivityRepositoryService")
public class ActivityRepositoryService implements ActivityRepository {

    @Autowired
    ActivityRepositoryJpa activityRepositoryJpa;

    public List<Activity> findAllByUser(User user){
        return activityRepositoryJpa.findAllByUser(user);
    }
    public void save(Activity activity){
        activityRepositoryJpa.save(activity);
    }
}
