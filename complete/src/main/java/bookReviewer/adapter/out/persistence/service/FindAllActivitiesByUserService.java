package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.entityToPersistence.UserMapper;
import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.ActivityMapper;
import bookReviewer.business.boundary.out.persistence.FindAllActivitiesByUser;
import bookReviewer.entity.user.Activity;
import bookReviewer.entity.user.User;
import bookReviewer.adapter.out.persistence.repository.ActivityRepository;
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
        return ActivityMapper.mapList(activityRepository.findAllByUser(UserMapper.map(user)));
    }
}
