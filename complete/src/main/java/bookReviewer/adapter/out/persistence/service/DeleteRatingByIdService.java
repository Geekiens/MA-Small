package bookReviewer.adapter.out.persistence.service;

import bookReviewer.application.boundary.out.persistence.DeleteRatingById;
import bookReviewer.adapter.out.persistence.repository.RatingRepository;

public class DeleteRatingByIdService implements DeleteRatingById {

    RatingRepository ratingRepository;

    public DeleteRatingByIdService(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    public void deleteRatingById(Long ratingId){
        ratingRepository.deleteById(ratingId);
    }
}
