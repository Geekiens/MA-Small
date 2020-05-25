package bookReviewer.business.useCase.query.getBockQuery;

import bookReviewer.business.boundary.in.useCase.query.GetBookQuery;
import bookReviewer.business.exception.ResourceNotFoundException;
import bookReviewer.business.mapper.BookMapper;
import bookReviewer.business.model.Book;
import bookReviewer.business.model.RatingSummary;
import bookReviewer.persistence.model.Rating;
import bookReviewer.persistence.repository.BookRepository;
import bookReviewer.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("GetBookQueryImpl")
public class GetBookQueryImpl implements GetBookQuery {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    RatingRepository ratingRepository;
    public Book getBook(long id) {
        bookReviewer.persistence.model.Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("book not found with id " + id));
        bookReviewer.business.model.Book bookWithRatings = BookMapper.book(book);
        RatingSummary ratingSummary = getAverageRating(book.getId());
        bookWithRatings.setAverageRating(ratingSummary.getAverageRating());
        bookWithRatings.setTotalVotes(ratingSummary.getTotalVotes());

        return bookWithRatings;
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
