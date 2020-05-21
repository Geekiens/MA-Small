package bookReviewer.business.model;

public class RatingBusiness {
    private long id;
    private int score;
    private String title;
    private String content;
    private Long userId;
    private String author;
    private BookBusiness book;

    public RatingBusiness() {
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

    public BookBusiness getBook() {
        return book;
    }

    public void setBook(BookBusiness book) {
        this.book = book;
    }
}
