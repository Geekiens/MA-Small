package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.entity.user.Activity;
import bookReviewer.entity.user.SubmissionsDate;

import java.util.ArrayList;
import java.util.List;

public final class ActivityMapper {
    public static Activity map(bookReviewer.persistence.model.Activity activityPersistence){
        Activity activity = new Activity();
        activity.setId(activityPersistence.getId());
        SubmissionsDate submissionsDate = new SubmissionsDate(activityPersistence.getSubmissionDate());
        activity.setSubmissionsDate(submissionsDate);
        activity.setActivityType(ActivityTypeMapper.map(activityPersistence.getActivityType()));
        return activity;
    }

    public static List<Activity> mapList(List<bookReviewer.persistence.model.Activity> activityPersistenceList){
        ArrayList<Activity> activities = new ArrayList<>();
        for (bookReviewer.persistence.model.Activity activityPersistence : activityPersistenceList){
            activities.add(map(activityPersistence));
        }
        return activities;
    }
}
