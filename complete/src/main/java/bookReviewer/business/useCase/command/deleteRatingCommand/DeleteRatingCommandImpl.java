package bookReviewer.business.useCase.command.deleteRatingCommand;

import bookReviewer.business.boundary.in.useCase.command.DeleteRatingCommand;
import bookReviewer.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("DeleteRatingCommandImpl")
public class DeleteRatingCommandImpl implements DeleteRatingCommand {

    @Autowired
    private RatingRepository ratingRepository;

    public void deleteRating(long id) {
        ratingRepository.deleteById(id);
    }
}
