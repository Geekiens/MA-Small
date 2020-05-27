package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.Rating;

public interface SaveRating {
    Long saveRating(Rating rating);
}
