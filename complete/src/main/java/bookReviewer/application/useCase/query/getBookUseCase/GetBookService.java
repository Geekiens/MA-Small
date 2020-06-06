package bookReviewer.application.useCase.query.getBookUseCase;

import bookReviewer.application.boundary.in.useCase.query.GetBookUseCase;
import bookReviewer.application.shared.exception.ResourceNotFoundException;
import bookReviewer.application.boundary.out.persistence.FindAllRatingsByBookId;
import bookReviewer.application.boundary.out.persistence.FindBookById;
import bookReviewer.entity.rating.AverageRatingCalculatorService;



public class GetBookService implements GetBookUseCase {

    FindBookById findBookById;

    FindAllRatingsByBookId findAllRatingsByBookId;

    public GetBookService(FindBookById findBookById, FindAllRatingsByBookId findAllRatingsByBookId){
        this.findBookById =findBookById;
        this.findAllRatingsByBookId = findAllRatingsByBookId;
    }

    public GetBookOutput getBook(long id) {
        bookReviewer.entity.book.Book book = findBookById.findBookById(id).orElseThrow(() -> new ResourceNotFoundException("book not found with id " + id));
        AverageRatingCalculatorService averageRatings = new AverageRatingCalculatorService(
                findAllRatingsByBookId.findAllRatingsByBookId(book.getId()));
        return BookOutputMapper.map(book, averageRatings.getAverageRating(), averageRatings.getTotalVotes());
    }
}
