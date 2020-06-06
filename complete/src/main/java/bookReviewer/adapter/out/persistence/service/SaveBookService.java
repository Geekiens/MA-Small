package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.entityToPersistence.BookMapper;
import bookReviewer.application.boundary.out.persistence.SaveBook;
import bookReviewer.entity.book.Book;
import bookReviewer.adapter.out.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SaveBookService")
public class SaveBookService implements SaveBook {
    @Autowired
    @Qualifier("BookRepositoryService")
    BookRepository bookRepository;

    public Long saveBook(Book book){
        return bookRepository.saveAndFlush(BookMapper.map(book));
    }
}
