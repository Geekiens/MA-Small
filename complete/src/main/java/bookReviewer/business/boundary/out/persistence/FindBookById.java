package bookReviewer.business.boundary.out.persistence;

import bookReviewer.entity.book.Book;

import java.util.Optional;

public interface FindBookById {
    Optional<Book> findBookById(Long bookId);
}
