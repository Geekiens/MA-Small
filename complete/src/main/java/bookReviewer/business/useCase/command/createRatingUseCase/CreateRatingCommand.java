package bookReviewer.business.useCase.command.createRatingUseCase;

public class CreateRatingCommand {
    private Long bookId;
    private Long userId;
    private Rating rating;

    public CreateRatingCommand() {
    }

    public CreateRatingCommand(Long bookId, Long userId, Rating rating) {
        this.bookId = bookId;
        this.userId = userId;
        this.rating = rating;
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
