package bookReviewer.business.useCase.command.deleteBookCommand;

import bookReviewer.business.boundary.in.useCase.command.DeleteBookCommand;
import bookReviewer.business.exception.ResourceNotFoundException;
import bookReviewer.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("DeleteBookCommandImpl")
public class DeleteBookCommandImpl implements DeleteBookCommand {
    @Autowired
    BookRepository bookRepository;

    public void deleteBook(long id) {
        bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("book doesn't exist with id: " + id));
        bookRepository.deleteById(id);
    }

}
