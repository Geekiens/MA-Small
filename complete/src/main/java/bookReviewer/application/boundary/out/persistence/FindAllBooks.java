package bookReviewer.application.boundary.out.persistence;

import bookReviewer.entity.book.Book;

import java.util.List;

public interface FindAllBooks {
    List<Book> findAllBooks();
}
