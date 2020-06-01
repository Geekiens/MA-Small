package bookReviewer.business.useCase.query.getBooksUseCase;

import bookReviewer.business.boundary.in.useCase.query.GetBooksUseCase;
import bookReviewer.business.boundary.out.persistence.FindAllBooks;
import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookId;
import bookReviewer.entity.rating.AverageRatingCalculatorService;
import bookReviewer.entity.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<GetBooksOutput> getBooks() {
        List<Book> books = findAllBooks.findAllBooks();
        List<GetBooksOutput> getBooksOutputList = new ArrayList<>();
        for (Book book : books) {
            AverageRatingCalculatorService averageRatings = new AverageRatingCalculatorService(
                    findAllRatingsByBookId.findAllRatingsByBookId(book.getId()));
            getBooksOutputList.add(BooksOutputMapper.map(book, averageRatings.getAverageRating(), averageRatings.getTotalVotes()));

        }
        return getBooksOutputList;
    }
}
