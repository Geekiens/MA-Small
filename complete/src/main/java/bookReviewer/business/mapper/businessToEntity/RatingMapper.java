package bookReviewer.business.mapper.businessToEntity;

import bookReviewer.business.model.RatingBusiness;
import bookReviewer.entity.rating.Rating;
import bookReviewer.entity.rating.RatingDetails;

public final class RatingMapper {
    public static Rating map(RatingBusiness ratingBusiness){
        Rating rating = new Rating();
        rating.setId(ratingBusiness.getId());
        rating.setBookId(ratingBusiness.getBook().getId());
        rating.setUserId(ratingBusiness.getUserId());

        RatingDetails ratingDetails = new RatingDetails();
        ratingDetails.setContent(ratingBusiness.getContent());
        ratingDetails.setScore(ratingBusiness.getScore());
        ratingDetails.setTitle(ratingBusiness.getTitle());
        rating.setRatingDetails(ratingDetails);

        return rating;
    }
}
