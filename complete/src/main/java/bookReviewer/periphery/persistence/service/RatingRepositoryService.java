package bookReviewer.periphery.persistence.service;

import bookReviewer.adapter.out.persistence.model.Rating;
import bookReviewer.adapter.out.persistence.repository.RatingRepository;
import bookReviewer.periphery.persistence.repository.RatingRepositoryJpa;


import java.util.List;
import java.util.Optional;

public class RatingRepositoryService implements RatingRepository {

    RatingRepositoryJpa ratingRepositoryJpa;

    public RatingRepositoryService(RatingRepositoryJpa ratingRepositoryJpa){
        this.ratingRepositoryJpa = ratingRepositoryJpa;
    }

    public List<Rating> findAllByBookId(Long bookId){
        return ratingRepositoryJpa.findAllByBookId(bookId);
    }

    public List<Rating> findAllByBookIdAndUserId(Long bookId, Long userId){
        return ratingRepositoryJpa.findAllByBookIdAndUserId(bookId, userId);
    }

    public List<Rating> findAllByBookIdAndContentNotNull(Long bookId){
        return ratingRepositoryJpa.findAllByBookIdAndContentNotNull(bookId);
    }

    public void deleteById(Long ratingId){
        ratingRepositoryJpa.deleteById(ratingId);
    }

    public Optional<Rating> findById(Long ratingId){
        return ratingRepositoryJpa.findById(ratingId);
    }

    public Long saveAndFlush(Rating rating){
        return ratingRepositoryJpa.saveAndFlush(rating).getId();
    }
}
