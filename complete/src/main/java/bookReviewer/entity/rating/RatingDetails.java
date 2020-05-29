package bookReviewer.entity.rating;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class RatingDetails {
    @Min(value = 1, message = "Score should not be less than 1")
    @Max(value = 5, message = "Score should not be greater than 5")
    private int score;
    private String title;
    private String content;

    public RatingDetails() {
    }

    public RatingDetails(int score, String title, String content, String author) {
        this.score = score;
        this.title = title;
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RatingDetails{" +
                "score=" + score +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

