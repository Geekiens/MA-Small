package bookReviewer.business.mapper;

import bookReviewer.business.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    public static Book book(bookReviewer.persistence.model.Book bookPersistence) {
        Book book = new Book();
        book.setId(bookPersistence.getId());
        book.setAuthor(bookPersistence.getAuthor());
        book.setTitle(bookPersistence.getTitle());
        book.setKeywords(bookPersistence.getKeywords());
        book.setGenre(bookPersistence.getGenre());
        book.setLanguages(bookPersistence.getLanguages());
        book.setIsbn(bookPersistence.getIsbn());
        book.setPages(bookPersistence.getPages());
        book.setPublishingYear(bookPersistence.getPublishingYear());
        book.setContent(bookPersistence.getContent());
        book.setPublisher(bookPersistence.getPublisher());
        return book;
    }

    public static ArrayList<Book> bookList(List<bookReviewer.persistence.model.Book> bookList) {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        for (bookReviewer.persistence.model.Book book : bookList) {
            bookArrayList.add(book(book));
        }
        return bookArrayList;
    }


}
