package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.UserMapper;
import bookReviewer.application.boundary.out.persistence.FindUserById;
import bookReviewer.entity.user.User;
import bookReviewer.adapter.out.persistence.repository.ActivityRepository;
import bookReviewer.adapter.out.persistence.repository.UserRepository;

import java.util.Optional;

public class FindUserByIdService implements FindUserById {

    ActivityRepository activityRepository;

    UserRepository userRepository;

    public FindUserByIdService(ActivityRepository activityRepository, UserRepository userRepository){
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    public Optional<User> findUserById(Long userId){
        bookReviewer.adapter.out.persistence.model.User user = userRepository.findById(userId).orElse(null);
        return Optional.of(UserMapper.map(user, activityRepository.findAllByUser(user)));
    }
}
