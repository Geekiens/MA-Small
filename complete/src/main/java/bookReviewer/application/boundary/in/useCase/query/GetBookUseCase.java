package bookReviewer.application.boundary.in.useCase.query;

import bookReviewer.application.useCase.query.getBookUseCase.GetBookOutput;

public interface GetBookUseCase {
    GetBookOutput getBook(long id);
}
