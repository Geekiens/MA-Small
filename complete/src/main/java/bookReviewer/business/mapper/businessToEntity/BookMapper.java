package bookReviewer.business.mapper.businessToEntity;

import bookReviewer.entity.book.Book;
import bookReviewer.entity.book.BookMetaDetails;
import bookReviewer.entity.book.BookUserDetails;

public class BookMapper {
    public static Book map(bookReviewer.business.model.BookBusiness bookBusiness){
        Book book = new Book();
        BookMetaDetails bookMetaDetails = new BookMetaDetails();
        bookMetaDetails.setAuthor(bookBusiness.getAuthor());
        bookMetaDetails.setGenre(bookBusiness.getGenre());
        bookMetaDetails.setLanguages(bookBusiness.getLanguages());
        bookMetaDetails.setPages(bookBusiness.getPages());
        bookMetaDetails.setPublisher(bookBusiness.getPublisher());
        bookMetaDetails.setPublishingYear(bookBusiness.getPublishingYear());
        bookMetaDetails.setTitle(bookBusiness.getTitle());

        BookUserDetails bookUserDetails = new BookUserDetails();
        bookUserDetails.setContent(bookBusiness.getContent());
        bookUserDetails.setKeywords(bookBusiness.getKeywords());

        book.setId(bookBusiness.getId());
        book.setIsbn(bookBusiness.getIsbn());
        book.setBookMetaDetails(bookMetaDetails);
        book.setBookUserDetails(bookUserDetails);

        return book;
    }
}
