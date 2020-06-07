package bookReviewer.application.boundary.out.persistence;

import bookReviewer.entity.rating.Rating;

import java.util.List;

public interface FindAllRatingsByBookId {
    List<Rating> findAllRatingsByBookId(Long bookId);
}
