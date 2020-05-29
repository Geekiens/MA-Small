package bookReviewer.adapter.out.persistence;

import bookReviewer.business.boundary.out.persistence.DeleteBookById;
import bookReviewer.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteBookByIdService implements DeleteBookById {
    @Autowired
    BookRepository bookRepository;
    public void deleteBookById(Long bookId){
        bookRepository.deleteById(bookId);
    }
}
