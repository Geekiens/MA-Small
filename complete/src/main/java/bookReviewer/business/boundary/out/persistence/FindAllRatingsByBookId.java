package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.Rating;

import java.util.List;

public interface FindAllRatingsByBookId {
    List<Rating> findAllRatingsByBookId(Long bookId);
}
