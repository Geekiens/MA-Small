package bookReviewer.periphery.persistence.service;

import bookReviewer.adapter.out.persistence.model.User;
import bookReviewer.adapter.out.persistence.repository.UserRepository;
import bookReviewer.periphery.persistence.repository.UserRepositoryJpa;


import java.util.List;
import java.util.Optional;

public class UserRepositoryService implements UserRepository {

    UserRepositoryJpa userRepositoryJpa;

    public UserRepositoryService(UserRepositoryJpa userRepositoryJpa){
        this.userRepositoryJpa = userRepositoryJpa;
    }

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
