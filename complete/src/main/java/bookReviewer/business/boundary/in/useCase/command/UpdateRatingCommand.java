package bookReviewer.business.boundary.in.useCase.command;

import bookReviewer.business.model.RatingBusiness;

public interface UpdateRatingCommand {
    void updateRating(long bookId, RatingBusiness rating);
}
