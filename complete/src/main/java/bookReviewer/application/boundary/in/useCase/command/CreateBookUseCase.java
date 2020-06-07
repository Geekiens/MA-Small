package bookReviewer.application.boundary.in.useCase.command;

import bookReviewer.application.useCase.command.createBookUseCase.CreateBookCommand;

public interface CreateBookUseCase {
    Long createBook(CreateBookCommand createBookCommand);
}
