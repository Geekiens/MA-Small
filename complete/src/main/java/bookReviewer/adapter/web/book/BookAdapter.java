package bookReviewer.adapter.web.book;

import java.util.List;

public interface BookAdapter {
    Long createBook(NewBook newBook, String token);
    void deleteBook(Long bookId, String token);
    BookWithOffers getBook(Long bookId);
    List<BookWithRatingInformation> getBooks();

}
