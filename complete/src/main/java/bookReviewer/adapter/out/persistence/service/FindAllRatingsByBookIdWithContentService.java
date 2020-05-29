package bookReviewer.adapter.out.persistence.service;

import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookIdWithContent;
import bookReviewer.persistence.model.Rating;
import bookReviewer.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("FindAllRatingsByBookIdWithContentService")
public class FindAllRatingsByBookIdWithContentService implements FindAllRatingsByBookIdWithContent {
    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> findAllRatingsByBookIdWithContent (Long bookId){
       return ratingRepository.findAllByBookIdAndContentNotNull(bookId);
    }
}
