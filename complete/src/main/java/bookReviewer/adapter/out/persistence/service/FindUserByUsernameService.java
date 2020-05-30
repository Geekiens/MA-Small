package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.UserMapper;
import bookReviewer.business.boundary.out.persistence.FindUserByUsername;
import bookReviewer.entity.user.User;
import bookReviewer.persistence.repository.ActivityRepository;
import bookReviewer.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("FindUserByUsernameService")
public class FindUserByUsernameService implements FindUserByUsername {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    public User findUserByUsername(String username){
        bookReviewer.persistence.model.User user = userRepository.findByUsername(username);
        return UserMapper.map(user, activityRepository.findAllByUser(user));
    }
}
