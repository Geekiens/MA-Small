package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.Rating;

import java.util.ArrayList;

public interface FindAllRatingsByBookIdWithContent {
    ArrayList<Rating> findAllRatingsByBookIdWithContent (Long bookId);
}
