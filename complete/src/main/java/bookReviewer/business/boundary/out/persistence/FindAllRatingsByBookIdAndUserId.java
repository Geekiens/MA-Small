package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.Rating;

import java.util.ArrayList;

public interface FindAllRatingsByBookIdAndUserId {
    ArrayList<Rating> findAllRatingsByBookIdAndUserId(Long bookId, Long userId);
}
