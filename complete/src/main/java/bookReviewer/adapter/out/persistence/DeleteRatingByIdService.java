package bookReviewer.adapter.out.persistence;

import bookReviewer.business.boundary.out.persistence.DeleteRatingById;
import bookReviewer.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteRatingByIdService implements DeleteRatingById {
    @Autowired
    RatingRepository ratingRepository;

    public void deleteRatingById(Long ratingId){
        ratingRepository.deleteById(ratingId);
    }
}
