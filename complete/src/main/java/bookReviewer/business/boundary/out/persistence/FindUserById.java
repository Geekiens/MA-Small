package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.User;

import java.util.Optional;

public interface FindUserById {
    Optional<User> findUserById(Long userId);
}
