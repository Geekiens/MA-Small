package bookReviewer.business.service;

import bookReviewer.business.exception.InvalidISBNException;
import bookReviewer.business.mapper.BookBusinessMapper;
import bookReviewer.business.model.BookBusiness;
import bookReviewer.business.util.JwtProvider;
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

    public List<BookBusiness> getBooks() {
        List<Book> books = bookRepository.findAll();

        return BookBusinessMapper.bookBusinessList(books);
    }

    public BookBusiness getBook(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("book not found with id " + id));
        return BookBusinessMapper.bookBusiness(book);
    }

    public Long createBook(BookBusiness book, String token) {
        checkISBN(book.getIsbn());
        if (token != null) {
            Claims claims = JwtProvider.decodeJWT(token);
            long reviewer = ((long) (int) claims.get("userId"));
            User user = userRepository.findById(reviewer).orElse(null);
            Activity activity = new Activity(new Date(), ActivityType.BOOK_CREATED, user);
            activityRepository.save(activity);
        }

        return bookRepository.saveAndFlush(BookBusinessMapper.book(book)).getId();
    }

    public void deleteBook(long id) {
        bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("book doesn't exist with id: " + id));
        bookRepository.deleteById(id);
    }

    public String getIsbnById(long id) {
        BookBusiness book = getBook(id);
        if (book == null) {
            return null;
        }
        return book.getIsbn();
    }

    private void checkISBN(String isbn){
        if (isbn == null) {
            return;
        }
        String pureISBN = isbn.replace("-", "");

        if(!pureISBN.matches("[0-9]+")) {
            throw new InvalidISBNException();
        }
        if (pureISBN.length() != 10 && pureISBN.length() != 13){
            throw new InvalidISBNException();
        }
        return;
    }

}