package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.UserMapper;
import bookReviewer.business.boundary.out.persistence.FindUserById;
import bookReviewer.entity.user.User;
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
        Optional<User> user = Optional.of(UserMapper.map(userRepository.findById(userId).get()));
        return user;
    }
}
