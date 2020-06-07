package bookReviewer.application.boundary.out.persistence;

import bookReviewer.entity.user.User;

import java.util.List;

public interface FindAllUsers {
    List<User> findAllUsers();
}
