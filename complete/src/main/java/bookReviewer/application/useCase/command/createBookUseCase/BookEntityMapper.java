package bookReviewer.application.useCase.command.createBookUseCase;

import bookReviewer.entity.book.BookMetaDetails;
import bookReviewer.entity.book.BookUserDetails;

public final class BookEntityMapper {
    public static bookReviewer.entity.book.Book map(Book bookInput){
        bookReviewer.entity.book.Book book = new bookReviewer.entity.book.Book();
        BookMetaDetails bookMetaDetails = new BookMetaDetails();
        bookMetaDetails.setAuthor(bookInput.getAuthor());
        bookMetaDetails.setGenre(bookInput.getGenre());
        bookMetaDetails.setLanguages(bookInput.getLanguages());
        bookMetaDetails.setPages(bookInput.getPages());
        bookMetaDetails.setPublisher(bookInput.getPublisher());
        bookMetaDetails.setPublishingYear(bookInput.getPublishingYear());
        bookMetaDetails.setTitle(bookInput.getTitle());

        BookUserDetails bookUserDetails = new BookUserDetails();
        bookUserDetails.setContent(bookInput.getContent());
        bookUserDetails.setKeywords(bookInput.getKeywords());

        book.setIsbn(bookInput.getIsbn());
        book.setBookMetaDetails(bookMetaDetails);
        book.setBookUserDetails(bookUserDetails);

        return book;
    }
}
