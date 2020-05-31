package bookReviewer.adapter.out.persistence.mapping.entityToPersistence;

import bookReviewer.entity.rating.Rating;
import bookReviewer.persistence.model.Book;

public final class RatingMapper {
    public static bookReviewer.persistence.model.Rating map(Rating rating, Book book, String author){
        bookReviewer.persistence.model.Rating ratingPersistence = new bookReviewer.persistence.model.Rating();
        ratingPersistence.setContent(rating.getRatingDetails().getContent());
        ratingPersistence.setId(rating.getId());
        ratingPersistence.setScore(rating.getRatingDetails().getScore());
        ratingPersistence.setTitle(rating.getRatingDetails().getTitle());
        ratingPersistence.setUserId(rating.getUserId());
        ratingPersistence.setBook(book);
        ratingPersistence.setAuthor(author);
        return ratingPersistence;
    }
}
