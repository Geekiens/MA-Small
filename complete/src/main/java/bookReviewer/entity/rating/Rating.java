package bookReviewer.entity.rating;

public class Rating {
    private long id;
    private RatingDetails ratingDetails;
    private long userId;
    private long bookId;

    public Rating() {
    }

    public Rating(long id, RatingDetails ratingDetails, long userId, long bookId) {
        this.id = id;
        this.ratingDetails = ratingDetails;
        this.userId = userId;
        this.bookId = bookId;
    }

    public Rating(RatingDetails ratingDetails, long userId, long bookId) {
        this.ratingDetails = ratingDetails;
        this.userId = userId;
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RatingDetails getRatingDetails() {
        return ratingDetails;
    }

    public void setRatingDetails(RatingDetails ratingDetails) {
        this.ratingDetails = ratingDetails;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", ratingDetails=" + ratingDetails.toString() +
                ", userId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}
