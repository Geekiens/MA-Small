package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.RatingMapper;
import bookReviewer.application.boundary.out.persistence.FindAllRatingsByBookIdWithContent;
import bookReviewer.entity.rating.Rating;
import bookReviewer.adapter.out.persistence.repository.RatingRepository;

import java.util.List;

public class FindAllRatingsByBookIdWithContentService implements FindAllRatingsByBookIdWithContent {

    RatingRepository ratingRepository;

    public FindAllRatingsByBookIdWithContentService(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findAllRatingsByBookIdWithContent (Long bookId){
       return RatingMapper.mapList(ratingRepository.findAllByBookIdAndContentNotNull(bookId));
    }
}
