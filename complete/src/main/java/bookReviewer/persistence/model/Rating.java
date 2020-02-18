package bookReviewer.persistence.model;

import javax.persistence.*;

@Entity
@Table(name = "RATING")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "stars")
    private int stars;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private String author;

    @Column(name = "ratedBookId")
    private long ratedBookId;

    public Rating(int stars, String title, String author, long ratedBookId) {
        this.stars = stars;
        this.title = title;
        this.author = author;
        this.ratedBookId = ratedBookId;
    }

    public long getRatedBookId() {
        return ratedBookId;
    }

    public void setRatedBookId(long ratedBookId) {
        this.ratedBookId = ratedBookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
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
}
