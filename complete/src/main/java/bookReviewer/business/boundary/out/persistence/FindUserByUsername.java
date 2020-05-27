package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.User;

import java.util.Optional;

public interface FindUserByUsername {
    Optional<User> findUserByUsername(String username);
}
