package bookReviewer.business.useCase.command.deleteRatingUseCase;

import bookReviewer.business.boundary.in.useCase.command.DeleteRatingUseCase;
import bookReviewer.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("DeleteRatingService")
public class DeleteRatingService implements DeleteRatingUseCase {

    @Autowired
    private RatingRepository ratingRepository;

    public void deleteRating(long id) {
        ratingRepository.deleteById(id);
    }
}
