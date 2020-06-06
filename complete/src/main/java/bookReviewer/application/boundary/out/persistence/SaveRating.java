package bookReviewer.application.boundary.out.persistence;

import bookReviewer.entity.rating.Rating;

public interface SaveRating {
    Long saveRating(Rating rating);
}
