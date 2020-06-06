package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.RatingMapper;
import bookReviewer.application.boundary.out.persistence.FindAllRatingsByBookIdAndUserId;
import bookReviewer.entity.rating.Rating;
import bookReviewer.adapter.out.persistence.repository.RatingRepository;

import java.util.List;

public class FindAllRatingsByBookIdAndUserIdService implements FindAllRatingsByBookIdAndUserId {

    RatingRepository ratingRepository;

    public FindAllRatingsByBookIdAndUserIdService(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findAllRatingsByBookIdAndUserId(Long bookId, Long userId){
       return RatingMapper.mapList(ratingRepository.findAllByBookIdAndUserId(bookId, userId));
    }
}
