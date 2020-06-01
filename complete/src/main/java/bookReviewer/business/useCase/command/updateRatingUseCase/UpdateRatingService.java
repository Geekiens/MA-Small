package bookReviewer.business.useCase.command.updateRatingUseCase;

import bookReviewer.business.boundary.in.useCase.command.UpdateRatingUseCase;
import bookReviewer.business.boundary.out.persistence.FindBookById;
import bookReviewer.business.boundary.out.persistence.SaveRating;

import bookReviewer.business.mapper.entityToBusiness.BookMapper;
import bookReviewer.business.model.BookBusiness;
import bookReviewer.entity.rating.Rating;
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

    public void updateRating(UpdateRatingCommand updateRatingCommand) {
        BookBusiness book = BookMapper.map(findBookById.findBookById(updateRatingCommand.getBookId()).orElse(null));
        Rating rating = RatingEntityMapper.map(updateRatingCommand.getRating());
        rating.setBookId(book.getId());
        System.out.println("rating: " + rating.toString());
        saveRating.saveRating(rating);
    }
}
