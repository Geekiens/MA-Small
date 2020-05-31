package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.entityToPersistence.ActivityMapper;
import bookReviewer.business.boundary.out.persistence.SaveActivity;
import bookReviewer.entity.user.Activity;
import bookReviewer.persistence.model.User;
import bookReviewer.persistence.repository.ActivityRepository;
import bookReviewer.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SaveActivityService")
public class SaveActivityService implements SaveActivity {
    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserRepository userRepository;


    public void saveActivity(Activity activity, Long userId){
        User user = userRepository.findById(userId).orElse(null);
        activityRepository.save(ActivityMapper.map(activity, user));
    }
}
