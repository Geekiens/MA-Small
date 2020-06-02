package bookReviewer.periphery.persistence.service;

import bookReviewer.adapter.out.persistence.model.Book;
import bookReviewer.adapter.out.persistence.repository.BookRepository;
import bookReviewer.periphery.persistence.repository.BookRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("BookRepositoryService")
public class BookRepositoryService implements BookRepository {

    @Autowired
    BookRepositoryJpa bookRepositoryJpa;

    public void deleteById(Long bookId){
        bookRepositoryJpa.deleteById(bookId);
    }

    public List<Book> findAll(){
        return bookRepositoryJpa.findAll();
    }

    public Optional<Book> findById(Long bookId){
        return bookRepositoryJpa.findById(bookId);
    }

    public Long saveAndFlush(Book book){
        return bookRepositoryJpa.saveAndFlush(book).getId();
    }
}
