package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.RatingMapper;
import bookReviewer.application.boundary.out.persistence.FindRatingById;
import bookReviewer.entity.rating.Rating;
import bookReviewer.adapter.out.persistence.repository.RatingRepository;

import java.util.Optional;

public class FindRatingByIdService implements FindRatingById {
    RatingRepository ratingRepository;

    public FindRatingByIdService(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    public Optional<Rating> findRatingById(Long ratingId){
        return Optional.of(RatingMapper.map(ratingRepository.findById(ratingId).get()));
    }
}
