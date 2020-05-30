package bookReviewer.business.boundary.out.persistence;

import bookReviewer.entity.user.User;

public interface FindUserByUsername {
    User findUserByUsername(String username);
}
