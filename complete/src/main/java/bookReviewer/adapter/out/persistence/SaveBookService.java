package bookReviewer.adapter.out.persistence;

import bookReviewer.business.boundary.out.persistence.SaveBook;
import bookReviewer.persistence.model.Book;
import bookReviewer.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SaveBookService")
public class SaveBookService implements SaveBook {
    @Autowired
    BookRepository bookRepository;

    public Long saveBook(Book book){
        return bookRepository.saveAndFlush(book).getId();
    }
}
