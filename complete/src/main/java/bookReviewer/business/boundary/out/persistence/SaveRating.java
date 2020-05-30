package bookReviewer.business.boundary.out.persistence;

import bookReviewer.entity.rating.Rating;

public interface SaveRating {
    Long saveRating(Rating rating);
}
