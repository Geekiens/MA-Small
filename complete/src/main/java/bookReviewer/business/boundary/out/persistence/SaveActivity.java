package bookReviewer.business.boundary.out.persistence;

import bookReviewer.entity.user.Activity;

public interface SaveActivity {
    void saveActivity(Activity activity, Long userId);
}
