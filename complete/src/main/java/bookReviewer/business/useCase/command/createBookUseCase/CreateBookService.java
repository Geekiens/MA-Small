package bookReviewer.business.useCase.command.createBookUseCase;

import bookReviewer.business.boundary.in.useCase.command.CreateBookUseCase;
import bookReviewer.business.boundary.out.persistence.FindUserById;
import bookReviewer.business.boundary.out.persistence.SaveActivity;
import bookReviewer.business.boundary.out.persistence.SaveBook;
import bookReviewer.business.shared.exception.InvalidISBNException;
import bookReviewer.entity.book.Book;
import bookReviewer.entity.user.Activity;
import bookReviewer.entity.user.SubmissionsDate;
import bookReviewer.entity.user.ActivityType;
import bookReviewer.entity.user.User;
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

    public Long createBook(CreateBookCommand createBookCommand) {
        Book book = BookEntityMapper.map(createBookCommand.getBook());
        if (book.isIsbnInvalid()){
            throw new InvalidISBNException();
        }
        if (createBookCommand.getUserId() != null){
            User user = findUserById.findUserById(createBookCommand.getUserId()).orElse(null);
            SubmissionsDate submissionsDate = new SubmissionsDate(new Date());
            Activity activity = new Activity(ActivityType.BOOK_CREATED, submissionsDate);
            saveActivity.saveActivity(activity, user.getId());
        }
        return saveBook.saveBook(book);
    }
}
