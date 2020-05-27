package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.Book;

import java.util.ArrayList;

public interface FindAllBooks {
    ArrayList<Book> findAllBooks();
}
