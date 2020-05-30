package bookReviewer.business.service;

import bookReviewer.business.boundary.out.persistence.FindRatingById;
import bookReviewer.business.mapper.entityToBusiness.RatingMapper;
import bookReviewer.business.model.RatingBusiness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("RatingService")
public class RatingService  {
    @Autowired
    @Qualifier("FindRatingByIdService")
    FindRatingById findRatingById;

    RatingMapper ratingMapper;
    public RatingBusiness getRating(long id) {
        // ratingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("rating not found with id " + id
        RatingBusiness rating = ratingMapper.map(findRatingById.findRatingById(id).orElse(null));
        return rating;
    }
}
