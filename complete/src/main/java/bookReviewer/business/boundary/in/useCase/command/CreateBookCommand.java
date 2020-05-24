package bookReviewer.business.boundary.in.useCase.command;

import bookReviewer.business.model.BookBusiness;

public interface CreateBookCommand {
    Long createBook(BookBusiness book, String token);
}
