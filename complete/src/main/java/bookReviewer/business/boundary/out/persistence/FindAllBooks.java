package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.Book;

import java.util.List;

public interface FindAllBooks {
    List<Book> findAllBooks();
}
