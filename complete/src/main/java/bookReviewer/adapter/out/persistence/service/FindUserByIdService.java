package bookReviewer.adapter.out.persistence.service;

import bookReviewer.business.boundary.out.persistence.FindUserById;
import bookReviewer.persistence.model.User;
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

    public Optional<User> findUserById(Long userId){
        return userRepository.findById(userId);
    }
}
