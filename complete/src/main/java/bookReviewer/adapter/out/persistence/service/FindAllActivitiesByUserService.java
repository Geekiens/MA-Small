package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.entityToPersistence.UserMapper;
import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.ActivityMapper;
import bookReviewer.application.boundary.out.persistence.FindAllActivitiesByUser;
import bookReviewer.entity.user.Activity;
import bookReviewer.entity.user.User;
import bookReviewer.adapter.out.persistence.repository.ActivityRepository;

import java.util.List;

public class FindAllActivitiesByUserService implements FindAllActivitiesByUser {

    ActivityRepository activityRepository;

    public FindAllActivitiesByUserService(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    public List<Activity> findAllActivitiesByUser(User user){
        return ActivityMapper.mapList(activityRepository.findAllByUser(UserMapper.map(user)));
    }
}
