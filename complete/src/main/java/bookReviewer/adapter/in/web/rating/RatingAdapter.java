package bookReviewer.adapter.in.web.rating;

import java.util.List;

public interface RatingAdapter {
    Long createRating(Long bookId, NewRating newRating, String token);
    void deleteRating(Long ratingId, String token);
    void updateRating(Long bookId, UpdateRating rating, String token);
    List<Rating> getRatingsOfBook(Long bookId);
    List<Rating> getRatingsOfBookWithContent(Long bookId);
}
