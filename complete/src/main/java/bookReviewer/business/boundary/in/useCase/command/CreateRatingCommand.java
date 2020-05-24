package bookReviewer.business.boundary.in.useCase.command;


import bookReviewer.business.model.RatingBusiness;

public interface CreateRatingCommand {
    Long createRating(Long bookId, RatingBusiness rating, String token);
}
