package bookReviewer.business.boundary.in.useCase.query;

import bookReviewer.business.model.Book;

public interface GetBookQuery {
    Book getBook(long id);
}
