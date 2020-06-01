package bookReviewer.adapter.out.persistence.mapping.entityToPersistence;

import bookReviewer.entity.rating.Rating;
import bookReviewer.adapter.out.persistence.model.Book;

public final class RatingMapper {
    public static bookReviewer.adapter.out.persistence.model.Rating map(Rating rating, Book book, String author){
        bookReviewer.adapter.out.persistence.model.Rating ratingPersistence = new bookReviewer.adapter.out.persistence.model.Rating();
        ratingPersistence.setContent(rating.getRatingDetails().getContent());
        ratingPersistence.setId(rating.getId());
        ratingPersistence.setScore(rating.getRatingDetails().getScore());
        ratingPersistence.setTitle(rating.getRatingDetails().getTitle());
        ratingPersistence.setUserId(rating.getUserId());
        ratingPersistence.setBook(book);
        return ratingPersistence;
    }
}
