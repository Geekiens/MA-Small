package bookReviewer.periphery.persistence.service;

import bookReviewer.adapter.out.persistence.model.User;
import bookReviewer.adapter.out.persistence.repository.UserRepository;
import bookReviewer.periphery.persistence.repository.UserRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("UserRepositoryService")
public class UserRepositoryService implements UserRepository {

    @Autowired
    UserRepositoryJpa userRepositoryJpa;

    public List<User> findAll(){
        return userRepositoryJpa.findAll();
    }

    public Optional<User> findById(Long userId){
        return userRepositoryJpa.findById(userId);
    }

    public User findByUsername(String username){
        return userRepositoryJpa.findByUsername(username);
    }

    public Long saveAndFlush(User user){
        return userRepositoryJpa.saveAndFlush(user).getId();
    }

}
