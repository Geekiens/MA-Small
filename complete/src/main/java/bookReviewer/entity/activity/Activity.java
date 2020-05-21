package bookReviewer.entity.activity;

public class Activity {
    private long id;
    private ActivityType activityType;
    private SubmissionsDate submissionsDate;
    private long userId;

    public Activity() {
    }

    public Activity(long id, ActivityType activityType, SubmissionsDate submissionsDate, long userId) {
        this.id = id;
        this.activityType = activityType;
        this.submissionsDate = submissionsDate;
        this.userId = userId;
    }

    public Activity(ActivityType activityType, SubmissionsDate submissionsDate, long userId) {
        this.activityType = activityType;
        this.submissionsDate = submissionsDate;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityType=" + activityType.toString() +
                ", submissionsDate=" + submissionsDate.toString() +
                ", userId=" + userId +
                '}';
    }

    public void setUserId(long userId) {
        this.userId = userId;


    }
}
