package bookReviewer.business;

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
        // ArrayList<Book> books = bookRepository.findAll();
        // return bookRepository.findAll();
        return new ArrayList<Book>();
    }

    public Book getBook(long id) {
        Book book =  bookRepository.findById(id).orElse(null);
        if (book == null) {
            return null;
        }
        return book;
    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(long id) {
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