package bookReviewer.business.boundary.in.useCase.query;

import bookReviewer.business.useCase.query.getRatingsOfBookWithContentUseCase.GetRatingsWithCommentOutput;

import java.util.List;

public interface GetRatingsOfBookWithContentUseCase {
    List<GetRatingsWithCommentOutput> getRatingsOfBookWithContent(Long bookId);
}
