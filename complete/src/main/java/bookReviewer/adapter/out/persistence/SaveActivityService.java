package bookReviewer.adapter.out.persistence;

import bookReviewer.business.boundary.out.persistence.SaveActivity;
import bookReviewer.persistence.model.Activity;
import bookReviewer.persistence.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SaveActivityService")
public class SaveActivityService implements SaveActivity {
    @Autowired
    ActivityRepository activityRepository;

    public void saveActivity(Activity activity){
        activityRepository.save(activity);
    }
}
