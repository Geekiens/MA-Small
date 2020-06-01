package bookReviewer.business.useCase.query.getBookUseCase;

import bookReviewer.business.boundary.in.useCase.query.GetBookUseCase;
import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookId;
import bookReviewer.business.boundary.out.persistence.FindBookById;
import bookReviewer.business.shared.exception.ResourceNotFoundException;
import bookReviewer.entity.rating.AverageRatingCalculatorService;
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
        AverageRatingCalculatorService averageRatings = new AverageRatingCalculatorService(
                findAllRatingsByBookId.findAllRatingsByBookId(book.getId()));
        return BookOutputMapper.map(book, averageRatings.getAverageRating(), averageRatings.getTotalVotes());
    }
}
