package bookReviewer.adapter.out.persistence.repository;

import bookReviewer.adapter.out.persistence.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository{
    List<User> findAll();
    Optional<User> findById(Long userId);
    User findByUsername(String username);
    Long saveAndFlush(User user);
}
