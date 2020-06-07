package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.BookMapper;
import bookReviewer.application.boundary.out.persistence.FindAllBooks;
import bookReviewer.entity.book.Book;
import bookReviewer.adapter.out.persistence.repository.BookRepository;

import java.util.List;

public class FindAllBooksService implements FindAllBooks {

    BookRepository bookRepository;

    public FindAllBooksService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public List<Book> findAllBooks(){
        return BookMapper.mapList(bookRepository.findAll());
    }
}
