package bookReviewer.application.useCase.command.setFavoriteBookUseCase;

import bookReviewer.application.boundary.in.useCase.command.SetFavoriteBookUseCase;
import bookReviewer.application.boundary.out.persistence.FindBookById;
import bookReviewer.application.boundary.out.persistence.FindUserById;
import bookReviewer.application.boundary.out.persistence.SaveBook;
import bookReviewer.application.boundary.out.persistence.SaveUser;
import bookReviewer.application.shared.exception.ResourceNotFoundException;
import bookReviewer.entity.book.Book;
import bookReviewer.entity.user.User;

public class SetFavoriteBookService implements SetFavoriteBookUseCase {

    FindUserById findUserById;
    FindBookById findBookById;
    SaveUser saveUser;
    SaveBook saveBook;

    public SetFavoriteBookService (FindUserById findUserById, FindBookById findBookById, SaveUser saveUser, SaveBook saveBook){
        this.findUserById = findUserById;
        this.findBookById = findBookById;
        this.saveUser = saveUser;
        this.saveBook = saveBook;
    }

    public void addFavorite(Long userId, Long bookId){
        User user = findUserById.findUserById(userId).orElseThrow(() -> new ResourceNotFoundException("user doesn't exist with id: " + userId));
        Book book = findBookById.findBookById(bookId).orElseThrow(() -> new ResourceNotFoundException("book doesn't exist with id: " + bookId));
        if (user.getFavoriteBook() != null){
            Book oldFavoriteBook = findBookById.findBookById(user.getFavoriteBook()).orElse(null);
            if (oldFavoriteBook != null){
                oldFavoriteBook.decreaseFavoriteCounter();
                saveBook.saveBook(oldFavoriteBook);
            }
        }
        book.increaseFavoriteCounter();
        user.setFavoriteBook(bookId);

        saveUser.saveUser(user);
        saveBook.saveBook(book);
    }
}
