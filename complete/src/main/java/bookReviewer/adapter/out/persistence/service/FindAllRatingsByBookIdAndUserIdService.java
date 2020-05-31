package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.RatingMapper;
import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookIdAndUserId;
import bookReviewer.entity.rating.Rating;
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
       return RatingMapper.mapList(ratingRepository.findAllByBookIdAndUserId(bookId, userId));
    }
}
