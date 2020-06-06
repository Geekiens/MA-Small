package bookReviewer.application.useCase.query.getBookUseCase;

import bookReviewer.application.boundary.in.useCase.query.GetBookUseCase;
import bookReviewer.application.shared.exception.ResourceNotFoundException;
import bookReviewer.application.boundary.out.persistence.FindAllRatingsByBookId;
import bookReviewer.application.boundary.out.persistence.FindBookById;
import bookReviewer.entity.rating.AverageRatingCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
