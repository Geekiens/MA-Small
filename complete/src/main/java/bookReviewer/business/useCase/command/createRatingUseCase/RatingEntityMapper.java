package bookReviewer.business.useCase.command.createRatingUseCase;

import bookReviewer.entity.rating.Rating;
import bookReviewer.entity.rating.RatingDetails;

public final class RatingEntityMapper {
    public static bookReviewer.entity.rating.Rating map(bookReviewer.business.useCase.command.createRatingUseCase.Rating ratingInput, Long bookId, Long userId){
        bookReviewer.entity.rating.Rating rating = new Rating();
        RatingDetails ratingDetails = new RatingDetails();
        ratingDetails.setContent(ratingInput.getContent());
        ratingDetails.setScore(ratingInput.getScore());
        ratingDetails.setTitle(ratingInput.getTitle());
        rating.setRatingDetails(ratingDetails);
        rating.setBookId(bookId);
        rating.setUserId(userId);
        return rating;
    }
}
