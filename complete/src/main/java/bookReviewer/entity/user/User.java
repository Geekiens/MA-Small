package bookReviewer.entity.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

public class User {
    private long id;
    @Email
    private String email;
    @NotNull
    private Role role;
    @NotNull
    private Credentials credentials;
    private List<Activity> activities;
    private String gender;

    public User() {
    }

    public User(String email, Role role, Credentials credentials) {
        this.email = email;
        this.role = role;
        this.credentials = credentials;
    }

    public User(long id, String email, Role role, Credentials credentials, List<Activity> activityList, String gender) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.credentials = credentials;
        this.activities = activityList;
        this.gender = gender;

    }

    public int calculateActivityPoints(){
        return getActivities().stream().mapToInt(activity -> {
                    switch (activity.getActivityType()) {
                        case BOOK_CREATED:
                            return 10;
                        case RATING_CREATED:
                            return 3;
                        case RATING_CREATED_WITH_COMMENT:
                            return 5;
                        case BOOK_DELETED_BY_ADMIN:
                            return -15;
                        case RATING_DELETED_BY_ADMIN:
                            return -20;
                        case RATING_DELETED_BY_MODERATOR:
                            return -10;
                        default: return 0;
                    }
                }
        ).sum();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", credentials=" + credentials.toString() +
                '}';
    }
}
