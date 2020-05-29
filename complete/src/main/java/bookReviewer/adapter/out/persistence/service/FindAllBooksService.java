package bookReviewer.adapter.out.persistence.service;

import bookReviewer.business.boundary.out.persistence.FindAllBooks;
import bookReviewer.persistence.model.Book;
import bookReviewer.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("FindAllBooksService")
public class FindAllBooksService implements FindAllBooks {
    @Autowired
    BookRepository bookRepository;
    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }
}
