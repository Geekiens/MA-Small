package bookReviewer.business.useCase.command.deleteBookUseCase;

import bookReviewer.business.boundary.in.useCase.command.DeleteBookUseCase;
import bookReviewer.business.exception.ResourceNotFoundException;
import bookReviewer.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("DeleteBookService")
public class DeleteBookService implements DeleteBookUseCase {
    @Autowired
    BookRepository bookRepository;

    public void deleteBook(long id) {
        bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("book doesn't exist with id: " + id));
        bookRepository.deleteById(id);
    }

}
