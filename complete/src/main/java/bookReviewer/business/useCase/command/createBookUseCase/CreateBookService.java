package bookReviewer.business.useCase.command.createBookUseCase;

import bookReviewer.business.boundary.in.useCase.command.CreateBookUseCase;
import bookReviewer.business.boundary.out.persistence.FindUserById;
import bookReviewer.business.boundary.out.persistence.SaveActivity;
import bookReviewer.business.boundary.out.persistence.SaveBook;
import bookReviewer.business.exception.InvalidISBNException;
import bookReviewer.business.mapper.businessToEntity.BookMapper;
import bookReviewer.business.model.BookBusiness;
import bookReviewer.business.util.JwtProvider;
import bookReviewer.entity.user.Activity;
import bookReviewer.entity.user.SubmissionsDate;
import bookReviewer.entity.user.ActivityType;
import bookReviewer.entity.user.User;
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
            SubmissionsDate submissionsDate = new SubmissionsDate(new Date());
            Activity activity = new Activity(ActivityType.BOOK_CREATED, submissionsDate);
            saveActivity.saveActivity(activity, user.getId());
        }

        return saveBook.saveBook(BookMapper.map(book));
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
