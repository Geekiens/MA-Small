package bookReviewer.periphery.persistence.service;

import bookReviewer.adapter.out.persistence.model.Book;
import bookReviewer.adapter.out.persistence.repository.BookRepository;
import bookReviewer.periphery.persistence.repository.BookRepositoryJpa;

import java.util.List;
import java.util.Optional;

public class BookRepositoryService implements BookRepository {

    BookRepositoryJpa bookRepositoryJpa;

    public BookRepositoryService(BookRepositoryJpa bookRepositoryJpa){
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

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
