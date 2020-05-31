package bookReviewer.business.useCase.command.updateRatingUseCase;

import bookReviewer.entity.rating.Rating;
import bookReviewer.entity.rating.RatingDetails;

public final class RatingEntityMapper {
    public static bookReviewer.entity.rating.Rating map(bookReviewer.business.useCase.command.updateRatingUseCase.Rating ratingInput){
        bookReviewer.entity.rating.Rating rating = new Rating();
        rating.setId(ratingInput.getId());
        rating.setUserId(ratingInput.getUserId());

        RatingDetails ratingDetails = new RatingDetails();
        ratingDetails.setContent(ratingInput.getContent());
        ratingDetails.setScore(ratingInput.getScore());
        ratingDetails.setTitle(ratingInput.getTitle());
        rating.setRatingDetails(ratingDetails);

        return rating;
    }
}
