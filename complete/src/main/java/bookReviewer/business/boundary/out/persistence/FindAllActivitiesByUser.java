package bookReviewer.business.boundary.out.persistence;

import bookReviewer.entity.user.Activity;
import bookReviewer.entity.user.User;

import java.util.List;

public interface FindAllActivitiesByUser {
        List<Activity> findAllActivitiesByUser(User user);
}
