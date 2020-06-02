package bookReviewer.adapter.out.persistence.repository;

import bookReviewer.adapter.out.persistence.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    void deleteById(Long bookId);
    List<Book> findAll();
    Optional<Book> findById(Long bookId);
    Long saveAndFlush(Book book);
}