package bookReviewer.entity.activity;

import java.util.Date;

public class SubmissionsDate {
    private Date submissionDate;

    public SubmissionsDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    @Override
    public String toString() {
        return "submissionsDate{" +
                "submissionDate=" + submissionDate +
                '}';
    }
}
