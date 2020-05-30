package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.entity.rating.Rating;
import bookReviewer.entity.rating.RatingDetails;

public final class RatingMapper {
    public static Rating map(bookReviewer.persistence.model.Rating ratingPersistence){
        Rating rating = new Rating();
        rating.setId(ratingPersistence.getId());
        rating.setBookId(ratingPersistence.getBook().getId());
        rating.setUserId(ratingPersistence.getUserId());

        RatingDetails ratingDetails = new RatingDetails();
        ratingDetails.setContent(ratingPersistence.getContent());
        ratingDetails.setScore(ratingPersistence.getScore());
        ratingDetails.setTitle(ratingPersistence.getTitle());
        rating.setRatingDetails(ratingDetails);

        return rating;
    }
}
