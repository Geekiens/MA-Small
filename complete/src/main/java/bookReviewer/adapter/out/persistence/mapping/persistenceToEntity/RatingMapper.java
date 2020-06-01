package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.entity.rating.Rating;
import bookReviewer.entity.rating.RatingDetails;

import java.util.ArrayList;
import java.util.List;

public final class RatingMapper {
    public static Rating map(bookReviewer.adapter.out.persistence.model.Rating ratingPersistence){
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

    public static List<Rating> mapList(List<bookReviewer.adapter.out.persistence.model.Rating> ratingList){
        ArrayList<Rating> ratings = new ArrayList<>();
        for(bookReviewer.adapter.out.persistence.model.Rating rating: ratingList){
            ratings.add(map(rating));
        }
        return ratings;
    }
}
