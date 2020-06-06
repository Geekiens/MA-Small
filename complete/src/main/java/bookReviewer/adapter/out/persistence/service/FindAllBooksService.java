package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.BookMapper;
import bookReviewer.application.boundary.out.persistence.FindAllBooks;
import bookReviewer.entity.book.Book;
import bookReviewer.adapter.out.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("FindAllBooksService")
public class FindAllBooksService implements FindAllBooks {
    @Autowired
    @Qualifier("BookRepositoryService")
    BookRepository bookRepository;
    public List<Book> findAllBooks(){
        return BookMapper.mapList(bookRepository.findAll());
    }
}
