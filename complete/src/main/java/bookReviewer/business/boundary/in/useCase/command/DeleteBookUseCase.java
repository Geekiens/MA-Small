package bookReviewer.business.boundary.in.useCase.command;

import bookReviewer.business.useCase.command.deleteBookUseCase.DeleteBookCommand;

public interface DeleteBookUseCase {
    void deleteBook(DeleteBookCommand deleteBookCommand);
}
