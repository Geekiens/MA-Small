package bookReviewer.adapter.out.persistence;

import bookReviewer.business.boundary.out.persistence.FindAllBooks;
import bookReviewer.persistence.model.Book;
import bookReviewer.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FindAllBooksService implements FindAllBooks {
    @Autowired
    BookRepository bookRepository;
    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }
}
