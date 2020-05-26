package bookReviewer.business.useCase.command.updateRatingUseCase;

import bookReviewer.business.boundary.in.useCase.command.UpdateRatingUseCase;
import bookReviewer.business.mapper.BookBusinessMapper;
import bookReviewer.business.mapper.RatingBusinessMapper;
import bookReviewer.business.model.RatingBusiness;
import bookReviewer.persistence.model.Book;
import bookReviewer.persistence.repository.BookRepository;
import bookReviewer.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("UpdateRatingService")
public class UpdateRatingService implements UpdateRatingUseCase {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private BookRepository bookRepository;

    public void updateRating(long bookId, RatingBusiness rating) {
        Book book = bookRepository.findById(bookId).orElse(null);
        rating.setBook(BookBusinessMapper.bookBusiness(book));

        System.out.println("rating: " + rating.toString());

        ratingRepository.save(RatingBusinessMapper.rating(rating));
    }

}
