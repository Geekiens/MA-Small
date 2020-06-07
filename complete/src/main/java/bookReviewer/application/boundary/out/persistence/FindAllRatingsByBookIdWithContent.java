package bookReviewer.application.boundary.out.persistence;

import bookReviewer.entity.rating.Rating;

import java.util.List;

public interface FindAllRatingsByBookIdWithContent {
    List<Rating> findAllRatingsByBookIdWithContent (Long bookId);
}
