package bookReviewer.adapter.in.web.book;

import java.util.List;

public interface BookAdapter {
    Long createBook(NewBook newBook, String token);
    void deleteBook(Long bookId, String token);
    void deleteBooks(List<Long> bookIds, String token);
    BookWithOffers getBook(Long bookId);
    List<BookWithRatingInformation> getBooks();
    void addFavorite(Long bookId, String token);
}
