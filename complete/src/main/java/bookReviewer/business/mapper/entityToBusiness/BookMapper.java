package bookReviewer.business.mapper.entityToBusiness;

import bookReviewer.business.model.BookBusiness;
import bookReviewer.entity.book.Book;

public final class BookMapper {
    public static BookBusiness map(Book book){
        BookBusiness bookBusiness = new BookBusiness();
        bookBusiness.setTitle(book.getBookMetaDetails().getTitle());
        bookBusiness.setAuthor(book.getBookMetaDetails().getAuthor());
        bookBusiness.setContent(book.getBookUserDetails().getContent());
        bookBusiness.setGenre(book.getBookMetaDetails().getGenre());
        bookBusiness.setId(book.getId());
        bookBusiness.setIsbn(book.getIsbn());
        bookBusiness.setKeywords(book.getBookUserDetails().getKeywords());
        bookBusiness.setLanguages(book.getBookMetaDetails().getLanguages());
        bookBusiness.setPages(book.getBookMetaDetails().getPages());
        bookBusiness.setPublisher(book.getBookMetaDetails().getPublisher());
        bookBusiness.setPublishingYear(book.getBookMetaDetails().getPublishingYear());
        return bookBusiness;
    }

}
