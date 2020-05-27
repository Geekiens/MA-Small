package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.Rating;

import java.util.ArrayList;

public interface FindAllRatingsByBookId {
    ArrayList<Rating> findAllRatingsByBookId(Long bookId);
}
