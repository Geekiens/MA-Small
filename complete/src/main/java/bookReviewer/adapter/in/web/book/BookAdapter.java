package bookReviewer.adapter.in.web.book;

import java.util.List;

public interface BookAdapter {
    Long createBook(NewBookDTO newBookDTO, String token);
    void deleteBook(Long bookId, String token);
    BookWithOffersDTO getBook(Long bookId);
    List<BookWithRatingInformationDTO> getBooks();

}
