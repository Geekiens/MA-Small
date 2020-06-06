package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.RatingMapper;
import bookReviewer.application.boundary.out.persistence.FindAllRatingsByBookId;
import bookReviewer.entity.rating.Rating;
import bookReviewer.adapter.out.persistence.repository.RatingRepository;

import java.util.List;

public class FindAllRatingsByBookIdService implements FindAllRatingsByBookId {

    RatingRepository ratingRepository;

    public FindAllRatingsByBookIdService(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findAllRatingsByBookId(Long bookId){
        return RatingMapper.mapList(ratingRepository.findAllByBookId(bookId));
    }
}
