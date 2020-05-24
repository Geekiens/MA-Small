package bookReviewer.business.boundary.in.useCase.query;

import bookReviewer.business.model.RatingBusiness;

import java.util.List;

public interface GetRatingsOfBookWithContentQuery {
    List<RatingBusiness> getRatingsOfBookWithContent(Long bookId);
}
