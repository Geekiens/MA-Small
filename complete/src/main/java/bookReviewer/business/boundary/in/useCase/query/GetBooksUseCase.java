package bookReviewer.business.boundary.in.useCase.query;

import bookReviewer.business.useCase.query.getBooksUseCase.GetBooksOutput;

import java.util.List;

public interface GetBooksUseCase {
    List<GetBooksOutput> getBooks();
}
