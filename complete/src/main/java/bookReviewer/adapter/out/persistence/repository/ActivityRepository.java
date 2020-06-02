package bookReviewer.adapter.out.persistence.repository;

import bookReviewer.adapter.out.persistence.model.Activity;
import bookReviewer.adapter.out.persistence.model.User;

import java.util.List;

public interface ActivityRepository {
    List<Activity> findAllByUser(User user);
    void save(Activity activity);
}
