package bookReviewer.adapter.out.persistence.repository;

import bookReviewer.adapter.out.persistence.model.Rating;

import java.util.List;
import java.util.Optional;


public interface RatingRepository {
    List<Rating> findAllByBookId(Long bookId);
    List<Rating> findAllByBookIdAndUserId(Long bookId, Long userId);
    List<Rating> findAllByBookIdAndContentNotNull(Long bookId);
    void deleteById(Long ratingId);
    Optional<Rating> findById(Long ratingId);
    Long saveAndFlush(Rating rating);
}
