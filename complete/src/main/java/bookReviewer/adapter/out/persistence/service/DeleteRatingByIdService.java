package bookReviewer.adapter.out.persistence.service;

import bookReviewer.application.boundary.out.persistence.DeleteRatingById;
import bookReviewer.adapter.out.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("DeleteRatingByIdService")
public class DeleteRatingByIdService implements DeleteRatingById {
    @Autowired
    @Qualifier("RatingRepositoryService")
    RatingRepository ratingRepository;

    public void deleteRatingById(Long ratingId){
        ratingRepository.deleteById(ratingId);
    }
}
