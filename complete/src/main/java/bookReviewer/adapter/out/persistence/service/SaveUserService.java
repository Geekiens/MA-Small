package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.entityToPersistence.UserMapper;
import bookReviewer.application.boundary.out.persistence.SaveUser;
import bookReviewer.entity.user.User;
import bookReviewer.adapter.out.persistence.repository.UserRepository;

public class SaveUserService implements SaveUser {

    UserRepository userRepository;

    public SaveUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Long saveUser(User user){
        return userRepository.saveAndFlush(UserMapper.map(user));
    }
}
