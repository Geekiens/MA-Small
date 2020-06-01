package bookReviewer.business.boundary.in.useCase.query;

import bookReviewer.business.useCase.query.getRatingsOfBookUseCase.GetRatingsOutput;

import java.util.List;

public interface GetRatingsOfBookUseCase {
    List<GetRatingsOutput> getRatingsOfBook(Long bookId);
}
