package bookReviewer.business.boundary.out.persistence;

import bookReviewer.entity.rating.Rating;

import java.util.List;

public interface FindAllRatingsByBookIdAndUserId {
    List<Rating> findAllRatingsByBookIdAndUserId(Long bookId, Long userId);
}
