package bookReviewer.application.boundary.out.persistence;

import bookReviewer.entity.rating.Rating;

import java.util.Optional;

public interface FindRatingById {
    Optional<Rating> findRatingById(Long ratingId);
}
