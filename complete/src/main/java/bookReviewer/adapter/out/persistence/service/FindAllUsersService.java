package bookReviewer.adapter.out.persistence.service;

import bookReviewer.business.boundary.out.persistence.FindAllUsers;
import bookReviewer.persistence.model.User;
import bookReviewer.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("FindAllUsersService")
public class FindAllUsersService implements FindAllUsers {
    @Autowired
    UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}
