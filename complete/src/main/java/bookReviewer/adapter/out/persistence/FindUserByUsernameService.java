package bookReviewer.adapter.out.persistence;

import bookReviewer.business.boundary.out.persistence.FindUserByUsername;
import bookReviewer.persistence.model.User;
import bookReviewer.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("FindUserByUsernameService")
public class FindUserByUsernameService implements FindUserByUsername {
    @Autowired
    UserRepository userRepository;

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
