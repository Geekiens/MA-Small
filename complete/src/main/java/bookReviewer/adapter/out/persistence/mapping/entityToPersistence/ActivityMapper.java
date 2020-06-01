package bookReviewer.adapter.out.persistence.mapping.entityToPersistence;

import bookReviewer.adapter.out.persistence.model.Activity;
import bookReviewer.adapter.out.persistence.model.User;

public final class ActivityMapper {
    public static Activity map(bookReviewer.entity.user.Activity activity, User user){
        Activity activityPersistence = new Activity();
        activityPersistence.setId(activity.getId());
        activityPersistence.setSubmissionDate(activity.getSubmissionsDate().getSubmissionDate());
        activityPersistence.setActivityType(ActivityTypeMapper.map(activity.getActivityType()));
        activityPersistence.setUser(user);
        return activityPersistence;
    }
}
