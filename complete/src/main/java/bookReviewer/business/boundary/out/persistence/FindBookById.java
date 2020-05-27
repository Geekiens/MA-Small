package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.Book;

import java.util.Optional;

public interface FindBookById {
    Optional<Book> findBookById(Long bookId);
}
