package bookReviewer.business.mapper;

import bookReviewer.business.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    public static Book book(bookReviewer.business.model.BookBusiness bookBusiness) {
        Book book = new Book();
        book.setId(bookBusiness.getId());
        book.setAuthor(bookBusiness.getAuthor());
        book.setTitle(bookBusiness.getTitle());
        book.setKeywords(bookBusiness.getKeywords());
        book.setGenre(bookBusiness.getGenre());
        book.setLanguages(bookBusiness.getLanguages());
        book.setIsbn(bookBusiness.getIsbn());
        book.setPages(bookBusiness.getPages());
        book.setPublishingYear(bookBusiness.getPublishingYear());
        book.setContent(bookBusiness.getContent());
        book.setPublisher(bookBusiness.getPublisher());
        return book;
    }

    public static ArrayList<Book> bookList(List<bookReviewer.business.model.BookBusiness> bookList) {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        for (bookReviewer.business.model.BookBusiness book : bookList) {
            bookArrayList.add(book(book));
        }
        return bookArrayList;
    }


}
