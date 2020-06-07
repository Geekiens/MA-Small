package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.entityToPersistence.BookMapper;
import bookReviewer.application.boundary.out.persistence.SaveBook;
import bookReviewer.entity.book.Book;
import bookReviewer.adapter.out.persistence.repository.BookRepository;

public class SaveBookService implements SaveBook {

    BookRepository bookRepository;

    public SaveBookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Long saveBook(Book book){
        return bookRepository.saveAndFlush(BookMapper.map(book));
    }
}
