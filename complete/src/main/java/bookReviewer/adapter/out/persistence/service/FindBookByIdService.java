package bookReviewer.adapter.out.persistence.service;

import bookReviewer.business.boundary.out.persistence.FindBookById;
import bookReviewer.persistence.model.Book;
import bookReviewer.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("FindBookByIdService")
public class FindBookByIdService implements FindBookById {
    @Autowired
    BookRepository bookRepository;

    public Optional<Book> findBookById(Long bookId){
        return bookRepository.findById(bookId);
    }
}
