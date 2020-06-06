package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.RatingMapper;
import bookReviewer.application.boundary.out.persistence.FindRatingById;
import bookReviewer.entity.rating.Rating;
import bookReviewer.adapter.out.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("FindRatingByIdService")
public class FindRatingByIdService implements FindRatingById {
    @Autowired
    @Qualifier("RatingRepositoryService")
    RatingRepository ratingRepository;

    public Optional<Rating> findRatingById(Long ratingId){
        return Optional.of(RatingMapper.map(ratingRepository.findById(ratingId).get()));
    }
}
