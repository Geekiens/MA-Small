package bookReviewer.business.useCase.command.createBookUseCase;

import bookReviewer.business.boundary.in.useCase.command.CreateBookUseCase;
import bookReviewer.business.boundary.out.persistence.FindUserById;
import bookReviewer.business.boundary.out.persistence.SaveActivity;
import bookReviewer.business.boundary.out.persistence.SaveBook;
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
    @Qualifier("SaveBookService")
    SaveBook saveBook;

    @Autowired
    @Qualifier("SaveActivityService")
    SaveActivity saveActivity;

    @Autowired
    @Qualifier("FindUserByIdService")
    FindUserById findUserById;

    public Long createBook(BookBusiness book, String token) {
        checkISBN(book.getIsbn());
        if (token != null) {
            Claims claims = JwtProvider.decodeJWT(token);
            long reviewer = ((long) (int) claims.get("userId"));
            User user = findUserById.findUserById(reviewer).orElse(null);
            Activity activity = new Activity(new Date(), ActivityType.BOOK_CREATED, user);
            saveActivity.saveActivity(activity);
        }

        return saveBook.saveBook(BookBusinessMapper.book(book));
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
