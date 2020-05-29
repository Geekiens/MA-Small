package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.Rating;

import java.util.List;

public interface FindAllRatingsByBookIdWithContent {
    List<Rating> findAllRatingsByBookIdWithContent (Long bookId);
}
