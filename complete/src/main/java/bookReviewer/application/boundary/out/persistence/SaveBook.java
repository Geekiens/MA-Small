package bookReviewer.application.boundary.out.persistence;

import bookReviewer.entity.book.Book;

public interface SaveBook {
    Long saveBook(Book book);
}
