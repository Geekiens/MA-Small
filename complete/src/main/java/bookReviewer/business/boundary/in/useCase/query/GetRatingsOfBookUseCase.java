package bookReviewer.business.boundary.in.useCase.query;

import bookReviewer.business.model.RatingBusiness;

import java.util.List;

public interface GetRatingsOfBookUseCase {
    List<RatingBusiness> getRatingsOfBook(Long bookId);
}
