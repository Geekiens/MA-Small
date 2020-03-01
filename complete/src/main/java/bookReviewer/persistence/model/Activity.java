package bookReviewer.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Activity {

    @Column(name="submissionDate")
    private Date submissionDate;

    @Column(name="type")
    private ActivityType activityType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
