package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.BookMapper;
import bookReviewer.business.boundary.out.persistence.FindBookById;
import bookReviewer.entity.book.Book;
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
        Optional<Book> book = Optional.of(BookMapper.map(bookRepository.findById(bookId).orElse(null)));
        return book;
    }
}
