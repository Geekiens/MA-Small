package bookReviewer.entity.rating;

public class RatingDetails {
    private int score;
    private String title;
    private String content;
    private String author;

    public RatingDetails() {
    }

    public RatingDetails(int score, String title, String content, String author) {
        this.score = score;
        this.title = title;
        this.content = content;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "RatingDetails{" +
                "score=" + score +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
