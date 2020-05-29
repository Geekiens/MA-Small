package bookReviewer.adapter.out.persistence;

import bookReviewer.business.boundary.out.persistence.SaveRating;
import bookReviewer.persistence.model.Rating;
import bookReviewer.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SaveRatingService")
public class SaveRatingService implements SaveRating {
    @Autowired
    RatingRepository ratingRepository;

    public Long saveRating(Rating rating){
        return ratingRepository.saveAndFlush(rating).getId();
    }
}
