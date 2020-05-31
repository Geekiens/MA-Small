package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.UserMapper;
import bookReviewer.business.boundary.out.persistence.FindUserById;
import bookReviewer.entity.user.User;
import bookReviewer.persistence.repository.ActivityRepository;
import bookReviewer.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("FindUserByIdService")
public class FindUserByIdService implements FindUserById {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    public Optional<User> findUserById(Long userId){
        bookReviewer.persistence.model.User user = userRepository.findById(userId).orElse(null);
        return Optional.of(UserMapper.map(user, activityRepository.findAllByUser(user)));
    }
}
