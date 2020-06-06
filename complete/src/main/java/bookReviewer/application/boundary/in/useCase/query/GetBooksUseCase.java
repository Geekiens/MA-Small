package bookReviewer.application.boundary.in.useCase.query;

import bookReviewer.application.useCase.query.getBooksUseCase.GetBooksOutput;

import java.util.List;

public interface GetBooksUseCase {
    List<GetBooksOutput> getBooks();
}
