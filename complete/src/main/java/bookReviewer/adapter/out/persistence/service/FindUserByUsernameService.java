package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.UserMapper;
import bookReviewer.application.boundary.out.persistence.FindUserByUsername;
import bookReviewer.entity.user.User;
import bookReviewer.adapter.out.persistence.repository.ActivityRepository;
import bookReviewer.adapter.out.persistence.repository.UserRepository;

public class FindUserByUsernameService implements FindUserByUsername {
    ActivityRepository activityRepository;

    UserRepository userRepository;

    public FindUserByUsernameService(ActivityRepository activityRepository, UserRepository userRepository){
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    public User findUserByUsername(String username){
        bookReviewer.adapter.out.persistence.model.User user = userRepository.findByUsername(username);
        return UserMapper.map(user, activityRepository.findAllByUser(user));
    }
}
