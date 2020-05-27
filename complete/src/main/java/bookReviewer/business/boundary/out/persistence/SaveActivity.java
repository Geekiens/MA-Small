package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.Activity;

public interface SaveActivity {
    void saveActivity(Activity activity);
}
