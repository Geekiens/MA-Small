package bookReviewer.business.boundary.in.useCase.command;

import bookReviewer.business.useCase.command.createBookUseCase.CreateBookCommand;

public interface CreateBookUseCase {
    Long createBook(CreateBookCommand createBookCommand);
}
