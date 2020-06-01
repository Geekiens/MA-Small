package bookReviewer.adapter.out.persistence.service;

import bookReviewer.business.boundary.out.persistence.DeleteBookById;
import bookReviewer.adapter.out.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("DeleteBookByIdService")
public class DeleteBookByIdService implements DeleteBookById {
    @Autowired
    BookRepository bookRepository;
    public void deleteBookById(Long bookId){
        bookRepository.deleteById(bookId);
    }
}
