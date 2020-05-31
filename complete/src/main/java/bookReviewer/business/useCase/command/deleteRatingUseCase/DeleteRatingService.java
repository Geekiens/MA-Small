package bookReviewer.business.useCase.command.deleteRatingUseCase;

import bookReviewer.business.boundary.in.useCase.command.DeleteRatingUseCase;
import bookReviewer.business.boundary.out.persistence.DeleteRatingById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("DeleteRatingService")
public class DeleteRatingService implements DeleteRatingUseCase {

    @Autowired
    @Qualifier("DeleteRatingByIdService")
    DeleteRatingById deleteRatingById;

    public void deleteRating(long id) {
        deleteRatingById.deleteRatingById(id);
    }
}
