package bookReviewer.application.useCase.command.createBookUseCase;

import bookReviewer.application.boundary.in.useCase.command.CreateBookUseCase;
import bookReviewer.application.shared.exception.InvalidISBNException;
import bookReviewer.application.boundary.out.persistence.FindUserById;
import bookReviewer.application.boundary.out.persistence.SaveActivity;
import bookReviewer.application.boundary.out.persistence.SaveBook;
import bookReviewer.entity.book.Book;
import bookReviewer.entity.user.Activity;
import bookReviewer.entity.user.SubmissionsDate;
import bookReviewer.entity.user.ActivityType;
import bookReviewer.entity.user.User;

import java.util.Date;

public class CreateBookService implements CreateBookUseCase {

    SaveBook saveBook;

    SaveActivity saveActivity;

    FindUserById findUserById;

    public CreateBookService(SaveBook saveBook, SaveActivity saveActivity, FindUserById findUserById){
        this.saveBook = saveBook;
        this.saveActivity = saveActivity;
        this.findUserById = findUserById;
    };


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
