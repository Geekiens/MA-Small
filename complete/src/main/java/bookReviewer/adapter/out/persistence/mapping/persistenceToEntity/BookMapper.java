package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.entity.book.Book;
import bookReviewer.entity.book.BookMetaDetails;
import bookReviewer.entity.book.BookUserDetails;

import java.util.ArrayList;
import java.util.List;

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
    public static List<Book> mapList(List<bookReviewer.persistence.model.Book> booksPersistence){
        ArrayList<Book> books = new ArrayList<>();
        for(bookReviewer.persistence.model.Book bookPersistence : booksPersistence){
            books.add(map(bookPersistence));
        }
        return books;
    }
}
