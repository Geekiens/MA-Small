package bookReviewer.business.useCase.command.createBookUseCase;

import bookReviewer.business.boundary.in.useCase.command.CreateBookUseCase;
import bookReviewer.business.exception.InvalidISBNException;
import bookReviewer.business.mapper.BookBusinessMapper;
import bookReviewer.business.model.BookBusiness;
import bookReviewer.business.util.JwtProvider;
import bookReviewer.persistence.model.Activity;
import bookReviewer.persistence.model.ActivityType;
import bookReviewer.persistence.model.User;
import bookReviewer.persistence.repository.ActivityRepository;
import bookReviewer.persistence.repository.BookRepository;
import bookReviewer.persistence.repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Qualifier("CreateBookService")
public class CreateBookService implements CreateBookUseCase {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserRepository userRepository;

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
