package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.RatingMapper;
import bookReviewer.application.boundary.out.persistence.FindAllRatingsByBookIdWithContent;
import bookReviewer.entity.rating.Rating;
import bookReviewer.adapter.out.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("FindAllRatingsByBookIdWithContentService")
public class FindAllRatingsByBookIdWithContentService implements FindAllRatingsByBookIdWithContent {
    @Autowired
    @Qualifier("RatingRepositoryService")
    RatingRepository ratingRepository;

    public List<Rating> findAllRatingsByBookIdWithContent (Long bookId){
       return RatingMapper.mapList(ratingRepository.findAllByBookIdAndContentNotNull(bookId));
    }
}
