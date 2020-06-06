package bookReviewer.application.useCase.command.updateRatingUseCase;

import bookReviewer.application.boundary.in.useCase.command.UpdateRatingUseCase;
import bookReviewer.application.boundary.out.persistence.FindRatingById;
import bookReviewer.application.shared.exception.ForbiddenResourceException;
import bookReviewer.application.shared.exception.ResourceNotFoundException;
import bookReviewer.application.boundary.out.persistence.FindBookById;
import bookReviewer.application.boundary.out.persistence.SaveRating;

import bookReviewer.entity.book.Book;
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

    @Autowired
    @Qualifier("FindRatingByIdService")
    FindRatingById findRatingById;

    public void updateRating(UpdateRatingCommand updateRatingCommand) {
        Book book = findBookById.findBookById(updateRatingCommand.getBookId()).orElse(null);
        Rating rating = RatingEntityMapper.map(updateRatingCommand.getRating());
        isOwnRating(rating.getId(), updateRatingCommand.getUserId());
        rating.setBookId(book.getId());
        System.out.println("rating: " + rating.toString());
        saveRating.saveRating(rating);
    }

    private void isOwnRating(Long ratingId, Long userId){
        Rating rating = findRatingById.findRatingById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating does not exist. Create it first"));
        if (rating.getUserId() != userId){
            throw new ForbiddenResourceException();
        }
    }
}
