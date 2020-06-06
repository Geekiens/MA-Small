package bookReviewer.application.useCase.query.getBooksUseCase;

import bookReviewer.application.boundary.in.useCase.query.GetBooksUseCase;
import bookReviewer.application.boundary.out.persistence.FindAllBooks;
import bookReviewer.application.boundary.out.persistence.FindAllRatingsByBookId;
import bookReviewer.entity.rating.AverageRatingCalculatorService;
import bookReviewer.entity.book.Book;


import java.util.ArrayList;
import java.util.List;


public class GetBooksService implements GetBooksUseCase {

    FindAllBooks findAllBooks;

    FindAllRatingsByBookId findAllRatingsByBookId;

    public GetBooksService(FindAllBooks findAllBooks,
                           FindAllRatingsByBookId findAllRatingsByBookId){
        this.findAllBooks = findAllBooks;
        this.findAllRatingsByBookId = findAllRatingsByBookId;
    }

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
