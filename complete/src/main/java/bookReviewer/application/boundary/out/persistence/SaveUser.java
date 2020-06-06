package bookReviewer.application.boundary.out.persistence;

import bookReviewer.entity.user.User;

public interface SaveUser {
    Long saveUser(User user);
}
