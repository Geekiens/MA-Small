package bookReviewer.adapter.out.persistence.service;

import bookReviewer.application.boundary.out.persistence.DeleteBookById;
import bookReviewer.adapter.out.persistence.repository.BookRepository;


public class DeleteBookByIdService implements DeleteBookById {

    BookRepository bookRepository;

    public DeleteBookByIdService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public void deleteBookById(Long bookId){
        bookRepository.deleteById(bookId);
    }
}
