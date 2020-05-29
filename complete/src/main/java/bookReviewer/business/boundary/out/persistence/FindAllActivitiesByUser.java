package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.Activity;
import bookReviewer.persistence.model.User;

import java.util.List;

public interface FindAllActivitiesByUser {
        List<Activity> findAllActivitiesByUser(User user);
}
