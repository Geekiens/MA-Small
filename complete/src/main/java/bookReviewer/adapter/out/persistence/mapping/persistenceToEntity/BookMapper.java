package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.entity.book.Book;
import bookReviewer.entity.book.BookMetaDetails;
import bookReviewer.entity.book.BookUserDetails;

public final class BookMapper {
    public static Book map(bookReviewer.persistence.model.Book bookPersistence){
        Book book = new Book();
        BookMetaDetails bookMetaDetails = new BookMetaDetails();
        bookMetaDetails.setAuthor(bookPersistence.getAuthor());
        bookMetaDetails.setGenre(bookPersistence.getGenre());
        bookMetaDetails.setLanguages(bookPersistence.getLanguages());
        bookMetaDetails.setPages(bookPersistence.getPages());
        bookMetaDetails.setPublisher(bookPersistence.getPublisher());
        bookMetaDetails.setPublishingYear(bookPersistence.getPublishingYear());
        bookMetaDetails.setTitle(bookPersistence.getTitle());

        BookUserDetails bookUserDetails = new BookUserDetails();
        bookUserDetails.setContent(bookPersistence.getContent());
        bookUserDetails.setKeywords(bookPersistence.getKeywords());

        book.setId(bookPersistence.getId());
        book.setIsbn(bookPersistence.getIsbn());
        book.setBookMetaDetails(bookMetaDetails);
        book.setBookUserDetails(bookUserDetails);

        return book;
    }
}
