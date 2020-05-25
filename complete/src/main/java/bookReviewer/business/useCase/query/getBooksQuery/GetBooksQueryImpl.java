package bookReviewer.business.useCase.query.getBooksQuery;

import bookReviewer.business.boundary.in.useCase.query.GetBooksQuery;
import bookReviewer.business.mapper.BookMapper;
import bookReviewer.business.model.RatingSummary;
import bookReviewer.persistence.model.Book;
import bookReviewer.persistence.model.Rating;
import bookReviewer.persistence.repository.BookRepository;
import bookReviewer.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("GetBooksQueryImpl")
public class GetBooksQueryImpl implements GetBooksQuery {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    RatingRepository ratingRepository;

    public List<bookReviewer.business.model.Book> getBooks() {
        List<Book> books = bookRepository.findAll();
        List<bookReviewer.business.model.Book> booksWithRating = BookMapper.bookList(books);
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
        List<Rating> ratings = ratingRepository.findAllByBookId(bookId);
        int sumOfRatings = 0;
        for (Rating rating : ratings) {
            sumOfRatings += rating.getScore();
            ratingSummary.addTotalVotes();
        }
        ratingSummary.setAverageRating(sumOfRatings * 1.0 / ratingSummary.getTotalVotes());
        return  ratingSummary;
    }
}
