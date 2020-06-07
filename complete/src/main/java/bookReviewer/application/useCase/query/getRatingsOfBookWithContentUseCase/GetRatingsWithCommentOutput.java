package bookReviewer.application.useCase.query.getRatingsOfBookWithContentUseCase;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GetRatingsWithCommentOutput {
    private long id;
    @Min(value = 1, message = "Score should not be less than 1")
    @Max(value = 5, message = "Score should not be greater than 5")
    private int score;
    private String title;
    @NotNull
    private String content;
    private Long userId;
    private String author;
    private Long bookId;

    public GetRatingsWithCommentOutput() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
