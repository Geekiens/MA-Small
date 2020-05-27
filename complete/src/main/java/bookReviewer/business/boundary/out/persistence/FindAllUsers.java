package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.User;

import java.util.ArrayList;

public interface FindAllUsers {
    ArrayList<User> findAllUsers();
}
