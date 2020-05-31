package bookReviewer.business.useCase.query.getBooksUseCase;

import bookReviewer.business.boundary.in.useCase.query.GetBooksUseCase;
import bookReviewer.business.boundary.out.persistence.FindAllBooks;
import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookId;
import bookReviewer.business.mapper.BookMapper;
import bookReviewer.business.shared.model.RatingSummary;
import bookReviewer.entity.rating.Rating;
import bookReviewer.entity.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("GetBooksService")
public class GetBooksService implements GetBooksUseCase {
    @Autowired
    @Qualifier("FindAllBooksService")
    FindAllBooks findAllBooks;

    @Autowired
    @Qualifier("FindAllRatingsByBookIdService")
    FindAllRatingsByBookId findAllRatingsByBookId;

    public List<bookReviewer.business.model.Book> getBooks() {
        List<Book> books = findAllBooks.findAllBooks();
        List<bookReviewer.business.model.Book> booksWithRating = BookMapper.bookList(bookReviewer.business.mapper.entityToBusiness.BookMapper.mapList(books));
        for (bookReviewer.business.model.Book book : booksWithRating) {
            System.out.println(book.toString());
            RatingSummary ratingSummary = getAverageRating(book.getId());
            book.setAverageRating(ratingSummary.getAverageRating());
            book.setTotalVotes(ratingSummary.getTotalVotes());
        }
        return booksWithRating;
    }
    private RatingSummary getAverageRating(Long bookId) {
        RatingSummary ratingSummary = new RatingSummary();
        List<Rating> ratings = findAllRatingsByBookId.findAllRatingsByBookId(bookId);
        int sumOfRatings = 0;
        for (Rating rating : ratings) {
            sumOfRatings += rating.getRatingDetails().getScore();
            ratingSummary.addTotalVotes();
        }
        ratingSummary.setAverageRating(sumOfRatings * 1.0 / ratingSummary.getTotalVotes());
        return  ratingSummary;
    }
}
