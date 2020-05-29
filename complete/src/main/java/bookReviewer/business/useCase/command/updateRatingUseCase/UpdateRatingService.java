package bookReviewer.business.useCase.command.updateRatingUseCase;

import bookReviewer.business.boundary.in.useCase.command.UpdateRatingUseCase;
import bookReviewer.business.boundary.out.persistence.FindBookById;
import bookReviewer.business.boundary.out.persistence.SaveRating;
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
    @Qualifier("SaveRatingService")
    SaveRating saveRating;

    @Autowired
    @Qualifier("FindBookByIdService")
    FindBookById findBookById;

    public void updateRating(long bookId, RatingBusiness rating) {
        Book book = findBookById.findBookById(bookId).orElse(null);
        rating.setBook(BookBusinessMapper.bookBusiness(book));

        System.out.println("rating: " + rating.toString());

        saveRating.saveRating(RatingBusinessMapper.rating(rating));
    }

}
