package bookReviewer.adapter.in.web.rating;

import java.util.List;

public interface RatingAdapter {
    Long createRating(Long bookId, NewRatingDTO newRatingDTO, String token);
    void deleteRating(Long ratingId, String token);
    void updateRating(Long bookId, UpdateRatingDTO rating, String token);
    List<RatingDTO> getRatingsOfBook(Long bookId);
    List<RatingDTO> getRatingsOfBookWithContent(Long bookId);
}
