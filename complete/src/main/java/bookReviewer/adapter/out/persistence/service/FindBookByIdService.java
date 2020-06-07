package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.BookMapper;
import bookReviewer.application.boundary.out.persistence.FindBookById;
import bookReviewer.entity.book.Book;
import bookReviewer.adapter.out.persistence.repository.BookRepository;

import java.util.Optional;


public class FindBookByIdService implements FindBookById {

    BookRepository bookRepository;

    public FindBookByIdService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Optional<Book> findBookById(Long bookId){
        return bookRepository.findById(bookId).isPresent()
                ? Optional.of(BookMapper.map(bookRepository.findById(bookId).get()))
                : Optional.empty();
    }
}
