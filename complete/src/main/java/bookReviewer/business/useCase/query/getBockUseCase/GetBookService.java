package bookReviewer.business.useCase.query.getBockUseCase;

import bookReviewer.business.boundary.in.useCase.query.GetBookUseCase;
import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookId;
import bookReviewer.business.boundary.out.persistence.FindBookById;
import bookReviewer.business.exception.ResourceNotFoundException;
import bookReviewer.business.mapper.BookMapper;
import bookReviewer.business.mapper.entityToBusiness.RatingMapper;
import bookReviewer.business.model.Book;
import bookReviewer.business.model.RatingBusiness;
import bookReviewer.business.model.RatingSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("GetBookService")
public class GetBookService implements GetBookUseCase {

    @Autowired
    @Qualifier("FindBookByIdService")
    FindBookById findBookById;

    @Autowired
    @Qualifier("FindAllRatingsByBookIdService")
    FindAllRatingsByBookId findAllRatingsByBookId;

    @Autowired
    RatingMapper ratingMapper;

    public Book getBook(long id) {
        bookReviewer.entity.book.Book book = findBookById.findBookById(id).orElseThrow(() -> new ResourceNotFoundException("book not found with id " + id));
        bookReviewer.business.model.Book bookWithRatings = BookMapper.book(bookReviewer.business.mapper.entityToBusiness.BookMapper.map(book));
        RatingSummary ratingSummary = getAverageRating(book.getId());
        bookWithRatings.setAverageRating(ratingSummary.getAverageRating());
        bookWithRatings.setTotalVotes(ratingSummary.getTotalVotes());

        return bookWithRatings;
    }

    private RatingSummary getAverageRating(Long bookId) {
        RatingSummary ratingSummary = new RatingSummary();
        List<RatingBusiness> ratings = ratingMapper.mapList(findAllRatingsByBookId.findAllRatingsByBookId(bookId));
        int sumOfRatings = 0;
        for (RatingBusiness rating : ratings) {
            sumOfRatings += rating.getScore();
            ratingSummary.addTotalVotes();
        }
        ratingSummary.setAverageRating(sumOfRatings * 1.0 / ratingSummary.getTotalVotes());
        return  ratingSummary;
    }
}
