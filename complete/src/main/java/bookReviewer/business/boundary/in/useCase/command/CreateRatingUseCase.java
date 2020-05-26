package bookReviewer.business.boundary.in.useCase.command;


import bookReviewer.business.model.RatingBusiness;

public interface CreateRatingUseCase {
    Long createRating(Long bookId, RatingBusiness rating, String token);
}
