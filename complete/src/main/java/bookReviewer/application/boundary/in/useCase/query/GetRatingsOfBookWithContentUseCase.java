package bookReviewer.application.boundary.in.useCase.query;

import bookReviewer.application.useCase.query.getRatingsOfBookWithContentUseCase.GetRatingsWithCommentOutput;

import java.util.List;

public interface GetRatingsOfBookWithContentUseCase {
    List<GetRatingsWithCommentOutput> getRatingsOfBookWithContent(Long bookId);
}
