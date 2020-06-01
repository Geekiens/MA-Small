package bookReviewer.business.useCase.command.updateRatingUseCase;


public class UpdateRatingCommand {
    private Long bookId;
    private Long userId;
    private Rating rating;

    public UpdateRatingCommand() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
