package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.User;

import java.util.List;

public interface FindAllUsers {
    List<User> findAllUsers();
}
