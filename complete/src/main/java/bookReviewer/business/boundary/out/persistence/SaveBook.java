package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.Book;

public interface SaveBook {
    Long saveBook(Book book);
}
