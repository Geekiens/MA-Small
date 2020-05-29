package bookReviewer.adapter.out.persistence;

import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookId;
import bookReviewer.persistence.model.Rating;
import bookReviewer.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("FindAllRatingsByBookIdService")
public class FindAllRatingsByBookIdService implements FindAllRatingsByBookId {
    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> findAllRatingsByBookId(Long bookId){
        return ratingRepository.findAllByBookId(bookId);
    }
}
