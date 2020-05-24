package bookReviewer.business.service;

import bookReviewer.business.boundary.in.useCase.command.CreateBookCommand;
import bookReviewer.business.boundary.in.useCase.command.DeleteBookCommand;
import bookReviewer.business.boundary.in.useCase.query.GetBookQuery;
import bookReviewer.business.boundary.in.useCase.query.GetBooksQuery;
import bookReviewer.business.exception.InvalidISBNException;
import bookReviewer.business.mapper.BookBusinessMapper;
import bookReviewer.business.mapper.BookMapper;
import bookReviewer.business.model.BookBusiness;
import bookReviewer.business.model.RatingSummary;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Qualifier("BookService")
public class BookService implements CreateBookCommand, DeleteBookCommand, GetBookQuery, GetBooksQuery {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    RatingService ratingService;

    OfferService offerService = new OfferService();

    public List<bookReviewer.business.model.Book> getBooks() {
        List<Book> books = bookRepository.findAll();
        List<bookReviewer.business.model.Book> booksWithRating = BookMapper.bookList(books);
        for (bookReviewer.business.model.Book book : booksWithRating) {
            System.out.println(book.toString());
            RatingSummary ratingSummary = ratingService.getAverageRating(book.getId());
            book.setAverageRating(ratingSummary.getAverageRating());
            book.setTotalVotes(ratingSummary.getTotalVotes());
        }
        return booksWithRating;
    }

    public bookReviewer.business.model.Book getBook(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("book not found with id " + id));
        bookReviewer.business.model.Book bookWithRatings = BookMapper.book(book);
        RatingSummary ratingSummary = ratingService.getAverageRating(book.getId());
        bookWithRatings.setAverageRating(ratingSummary.getAverageRating());
        bookWithRatings.setTotalVotes(ratingSummary.getTotalVotes());

        return bookWithRatings;
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
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("book not found with id " + id));
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