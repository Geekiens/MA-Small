package bookReviewer.business;

import bookReviewer.business.Util.JwtProvider;
import bookReviewer.business.exception.ResourceNotFoundException;
import bookReviewer.persistence.model.Activity;
import bookReviewer.persistence.model.ActivityType;
import bookReviewer.persistence.model.Book;
import bookReviewer.persistence.model.User;
import bookReviewer.persistence.repository.ActivityRepository;
import bookReviewer.persistence.repository.BookRepository;
import bookReviewer.persistence.repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    OfferService offerService = new OfferService();

    public List<Book> getBooks() {
        //Book[] ps = restTemplate.getForEntity(productsURL, Product[].class).getBody();
        List<Book> books = bookRepository.findAll();
        return books;
    }

    public Book getBook(long id) {
        return  bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("book not found with id " + id));
    }

    public void createBook(Book book, String token) {
        Claims claims = JwtProvider.decodeJWT(token);
        long reviewer =((long) (int) claims.get("userId"));
        User user = userRepository.findById(reviewer).orElse(null);
        Activity activity = new Activity(new Date(), ActivityType.BOOK_CREATED, user);
        activityRepository.save(activity);
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