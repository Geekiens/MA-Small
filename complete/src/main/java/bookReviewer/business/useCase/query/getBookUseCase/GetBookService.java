package bookReviewer.business.useCase.query.getBookUseCase;

import bookReviewer.business.boundary.in.useCase.query.GetBookUseCase;
import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookId;
import bookReviewer.business.boundary.out.persistence.FindBookById;
import bookReviewer.business.exception.ResourceNotFoundException;
import bookReviewer.business.shared.model.RatingSummary;
import bookReviewer.entity.rating.Rating;
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

    public GetBookOutput getBook(long id) {
        bookReviewer.entity.book.Book book = findBookById.findBookById(id).orElseThrow(() -> new ResourceNotFoundException("book not found with id " + id));
        RatingSummary ratingSummary = getAverageRating(book.getId());
        return BookOutputMapper.map(book, ratingSummary);
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
