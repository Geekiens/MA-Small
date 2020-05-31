package bookReviewer.business.useCase.command.updateRatingUseCase;

import bookReviewer.business.boundary.in.useCase.command.UpdateRatingUseCase;
import bookReviewer.business.boundary.out.persistence.FindBookById;
import bookReviewer.business.boundary.out.persistence.SaveRating;

import bookReviewer.business.mapper.businessToEntity.RatingMapper;
import bookReviewer.business.mapper.entityToBusiness.BookMapper;
import bookReviewer.business.model.BookBusiness;
import bookReviewer.business.model.RatingBusiness;
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
        BookBusiness book = BookMapper.map(findBookById.findBookById(bookId).orElse(null));
        rating.setBook(book);
        System.out.println("rating: " + rating.toString());

        saveRating.saveRating(RatingMapper.map(rating));
    }
}
