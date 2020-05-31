package bookReviewer.entity.user;

public class Activity {
    private long id;
    private ActivityType activityType;
    private SubmissionsDate submissionsDate;

    public Activity() {
    }

    public Activity(long id, ActivityType activityType, SubmissionsDate submissionsDate) {
        this.id = id;
        this.activityType = activityType;
        this.submissionsDate = submissionsDate;
    }

    public Activity(ActivityType activityType, SubmissionsDate submissionsDate) {
        this.activityType = activityType;
        this.submissionsDate = submissionsDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public SubmissionsDate getSubmissionsDate() {
        return submissionsDate;
    }

    public void setSubmissionsDate(SubmissionsDate submissionsDate) {
        this.submissionsDate = submissionsDate;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityType=" + activityType.toString() +
                ", submissionsDate=" + submissionsDate.toString() +
                '}';
    }
}
