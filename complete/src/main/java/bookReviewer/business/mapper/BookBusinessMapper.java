package bookReviewer.business.mapper;

import bookReviewer.business.model.BookBusiness;
import bookReviewer.persistence.model.Book;

import java.util.ArrayList;
import java.util.List;

public final class BookBusinessMapper {

    public static Book book(BookBusiness bookBusiness){
        Book book = new Book();
        book.setId(bookBusiness.getId());
        book.setAuthor(bookBusiness.getAuthor());
        book.setTitle(bookBusiness.getTitle());
        book.setGenre(bookBusiness.getGenre());
        book.setKeywords(bookBusiness.getKeywords());
        book.setLanguages(bookBusiness.getLanguages());
        book.setPublisher(bookBusiness.getPublisher());
        book.setPages(bookBusiness.getPages());
        book.setIsbn(bookBusiness.getIsbn());
        book.setPublishingYear(bookBusiness.getPublishingYear());
        book.setContent(bookBusiness.getContent());
        return book;
    }

    public static BookBusiness bookBusiness(Book book){
        BookBusiness bookBusiness = new BookBusiness();
        bookBusiness.setId(book.getId());
        bookBusiness.setAuthor(book.getAuthor());
        bookBusiness.setTitle(book.getTitle());
        bookBusiness.setKeywords(book.getKeywords());
        bookBusiness.setGenre(book.getGenre());
        bookBusiness.setLanguages(book.getLanguages());
        bookBusiness.setPages(book.getPages());
        bookBusiness.setIsbn(book.getIsbn());
        bookBusiness.setPublishingYear(book.getPublishingYear());
        bookBusiness.setContent(book.getContent());
        bookBusiness.setPublisher(book.getPublisher());
        return bookBusiness;
    }

    public static ArrayList<BookBusiness> bookBusinessList(List<Book> bookList){
        ArrayList<BookBusiness> bookBusinessArrayList = new ArrayList<>();
        for (Book book: bookList) {
            bookBusinessArrayList.add(bookBusiness(book));
        }
        return bookBusinessArrayList;
    }

    public static ArrayList<Book> bookList(List<BookBusiness> bookBusinessList){
        ArrayList<Book> bookArrayList = new ArrayList<>();
        for (BookBusiness bookBusiness: bookBusinessList) {
            bookArrayList.add(book(bookBusiness));
        }
        return bookArrayList;
    }
}
