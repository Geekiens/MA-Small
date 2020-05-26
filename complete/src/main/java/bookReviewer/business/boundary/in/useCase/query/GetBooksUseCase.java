package bookReviewer.business.boundary.in.useCase.query;

import bookReviewer.business.model.Book;

import java.util.List;

public interface GetBooksUseCase {
    List<Book> getBooks();
}
