package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.entityToPersistence.ActivityMapper;
import bookReviewer.application.boundary.out.persistence.SaveActivity;
import bookReviewer.entity.user.Activity;
import bookReviewer.adapter.out.persistence.model.User;
import bookReviewer.adapter.out.persistence.repository.ActivityRepository;
import bookReviewer.adapter.out.persistence.repository.UserRepository;

public class SaveActivityService implements SaveActivity {

    ActivityRepository activityRepository;

    UserRepository userRepository;

    public SaveActivityService(ActivityRepository activityRepository, UserRepository userRepository){
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }


    public void saveActivity(Activity activity, Long userId){
        User user = userRepository.findById(userId).orElse(null);
        activityRepository.save(ActivityMapper.map(activity, user));
    }
}
