package bookReviewer.business.service;

import bookReviewer.business.mapper.RatingBusinessMapper;
import bookReviewer.business.model.RatingBusiness;
import bookReviewer.business.exception.ResourceNotFoundException;
import bookReviewer.persistence.model.*;
import bookReviewer.persistence.repository.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("RatingService")
public class RatingService  {
    @Autowired
    RatingRepository ratingRepository;

    public RatingBusiness getRating(long id) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("rating not found with id " + id));
        return RatingBusinessMapper.ratingBusiness(rating);
    }
}
