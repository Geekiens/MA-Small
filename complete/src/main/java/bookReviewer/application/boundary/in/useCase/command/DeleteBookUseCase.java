package bookReviewer.application.boundary.in.useCase.command;

import bookReviewer.application.useCase.command.deleteBookUseCase.DeleteBookCommand;

public interface DeleteBookUseCase {
    void deleteBook(DeleteBookCommand deleteBookCommand);
}
