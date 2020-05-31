package bookReviewer.business.boundary.in.useCase.query;

import bookReviewer.business.useCase.query.getBookUseCase.GetBookOutput;

public interface GetBookUseCase {
    GetBookOutput getBook(long id);
}
