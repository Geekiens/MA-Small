package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.Activity;
import bookReviewer.persistence.model.User;

import java.util.ArrayList;

public interface FindAllActivitiesByUser {
        ArrayList<Activity> findAllUsers(User user);
}
