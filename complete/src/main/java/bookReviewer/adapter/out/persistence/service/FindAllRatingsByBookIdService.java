package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.RatingMapper;
import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookId;
import bookReviewer.entity.rating.Rating;
import bookReviewer.adapter.out.persistence.repository.RatingRepository;
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
        return RatingMapper.mapList(ratingRepository.findAllByBookId(bookId));
    }
}
