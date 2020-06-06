package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.UserMapper;
import bookReviewer.application.boundary.out.persistence.FindUserByUsername;
import bookReviewer.entity.user.User;
import bookReviewer.adapter.out.persistence.repository.ActivityRepository;
import bookReviewer.adapter.out.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("FindUserByUsernameService")
public class FindUserByUsernameService implements FindUserByUsername {
    @Autowired
    @Qualifier("UserRepositoryService")
    UserRepository userRepository;

    @Autowired
    @Qualifier("ActivityRepositoryService")
    ActivityRepository activityRepository;

    public User findUserByUsername(String username){
        bookReviewer.adapter.out.persistence.model.User user = userRepository.findByUsername(username);
        return UserMapper.map(user, activityRepository.findAllByUser(user));
    }
}
