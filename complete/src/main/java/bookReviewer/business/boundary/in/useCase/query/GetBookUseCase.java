package bookReviewer.business.boundary.in.useCase.query;

import bookReviewer.business.model.Book;

public interface GetBookUseCase {
    Book getBook(long id);
}
