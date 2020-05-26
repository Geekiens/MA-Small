package bookReviewer.business.boundary.in.useCase.command;

import bookReviewer.business.model.RatingBusiness;

public interface UpdateRatingUseCase {
    void updateRating(long bookId, RatingBusiness rating);
}
