package bookReviewer.application.boundary.in.useCase.query;

import bookReviewer.application.useCase.query.getRatingsOfBookUseCase.GetRatingsOutput;

import java.util.List;

public interface GetRatingsOfBookUseCase {
    List<GetRatingsOutput> getRatingsOfBook(Long bookId);
}
