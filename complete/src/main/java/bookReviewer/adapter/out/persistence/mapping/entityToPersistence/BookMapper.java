package bookReviewer.adapter.out.persistence.mapping.entityToPersistence;

import bookReviewer.adapter.out.persistence.model.Book;

public final class BookMapper {
    public static Book map(bookReviewer.entity.book.Book book){
        Book bookPersistence = new Book();
        bookPersistence.setTitle(book.getBookMetaDetails().getTitle());
        bookPersistence.setAuthor(book.getBookMetaDetails().getAuthor());
        bookPersistence.setContent(book.getBookUserDetails().getContent());
        bookPersistence.setGenre(book.getBookMetaDetails().getGenre());
        bookPersistence.setId(book.getId());
        bookPersistence.setIsbn(book.getIsbn());
        bookPersistence.setKeywords(book.getBookUserDetails().getKeywords());
        bookPersistence.setLanguages(book.getBookMetaDetails().getLanguages());
        bookPersistence.setPages(book.getBookMetaDetails().getPages());
        bookPersistence.setPublisher(book.getBookMetaDetails().getPublisher());
        bookPersistence.setPublishingYear(book.getBookMetaDetails().getPublishingYear());
        bookPersistence.setFavoriteCounter(book.getFavoriteCounter());
        return bookPersistence;
    }
}