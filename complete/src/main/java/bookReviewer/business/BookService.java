package bookReviewer.business;

import bookReviewer.business.exception.ResourceNotFoundException;
import bookReviewer.business.model.Offer;
import bookReviewer.persistence.model.Book;
import bookReviewer.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    OfferService offerService = new OfferService();

    public List<Book> getBooks() {
        //Book[] ps = restTemplate.getForEntity(productsURL, Product[].class).getBody();
        List<Book> books = bookRepository.findAll();
        return books;
    }

    public Book getBook(long id) {
        return  bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("book not found with id " + id));
    }

    public void createBook(Book book) {

        bookRepository.save(book);
    }

    public void deleteBook(long id) {
        System.out.println("Delete Book accessed");
        bookRepository.deleteById(id);
    }

    public String getIsbnById(long id) {
        Book book = getBook(id);
        if (book == null) {
            return null;
        }
        return book.getIsbn();
    }

}