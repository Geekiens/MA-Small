package bookReviewer.business;

import bookReviewer.persistence.model.Rating;
import bookReviewer.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RatingService {
    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> getRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings;
    }

    public List<Rating> getRatingsBy() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings;
    }

    public Rating getRating(long id) {
        Rating rating =  ratingRepository.findById(id).orElse(null);
        return rating;
    }

    public void createRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public void deleteRating(long id) {
        ratingRepository.deleteById(id);
    }


}


