package bookReviewer.adapter.out.persistence;

import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookIdAndUserId;
import bookReviewer.persistence.model.Rating;
import bookReviewer.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("FindAllRatingsByBookIdAndUserIdService")
public class FindAllRatingsByBookIdAndUserIdService implements FindAllRatingsByBookIdAndUserId {
    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> findAllRatingsByBookIdAndUserId(Long bookId, Long userId){
       return ratingRepository.findAllByBookIdAndUserId(bookId, userId);
    }
}
