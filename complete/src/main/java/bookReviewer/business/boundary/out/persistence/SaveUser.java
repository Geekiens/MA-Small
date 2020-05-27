package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.User;

public interface SaveUser {
    Long saveUser(User user);
}
