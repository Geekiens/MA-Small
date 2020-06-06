package bookReviewer.application.boundary.out.persistence;

import bookReviewer.entity.user.User;

import java.util.Optional;

public interface FindUserById {
    Optional<User> findUserById(Long userId);
}
