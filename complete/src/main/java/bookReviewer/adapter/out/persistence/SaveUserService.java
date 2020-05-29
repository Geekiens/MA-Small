package bookReviewer.adapter.out.persistence;

import bookReviewer.business.boundary.out.persistence.SaveUser;
import bookReviewer.persistence.model.User;
import bookReviewer.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SaveUserService")
public class SaveUserService implements SaveUser {
    @Autowired
    UserRepository userRepository;

    public Long saveUser(User user){
        return userRepository.saveAndFlush(user).getId();
    }
}
