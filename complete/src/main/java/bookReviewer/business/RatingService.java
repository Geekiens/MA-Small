package bookReviewer.business;

import bookReviewer.business.model.RatingSummary;
import bookReviewer.persistence.model.Book;
import bookReviewer.persistence.model.Rating;
import bookReviewer.persistence.repository.BookRepository;
import bookReviewer.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    BookRepository bookRepository;

    public List<Rating> getRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings;
    }

    public List<Rating> findRatingsById(Long bookId) {
        List<Rating> ratings = ratingRepository.findAllByBookId(bookId);
        return ratings;
    }

    public RatingSummary getAverageRating(Long bookId) {
        List<Rating> ratings = ratingRepository.findAllByBookId(bookId);
        RatingSummary ratingSummary = new RatingSummary();
        int sumOfRatings = 0;
        for (Rating rating : ratings) {
            sumOfRatings += rating.getStars();
            ratingSummary.addTotalVotes();
        }
        ratingSummary.setAverageRating(sumOfRatings * 1.0 / ratingSummary.getTotalVotes());
        return  ratingSummary;
    }

    public List<Rating> getRatingsByIdWithContent(Long bookId) {
        List<Rating> ratings = ratingRepository.findAllByBookIdAndContentNotNull(bookId);
        return ratings;
    }

    public Rating getRating(long id) {
        Rating rating =  ratingRepository.findById(id).orElse(null);
        return rating;
    }

    public void createRating(Long bookId, Rating rating) {
        System.out.println(bookId);
        Book book = bookRepository.findById(bookId).orElse(null);
        rating.setBook(book);
        ratingRepository.save(rating);
    }

    public void deleteRating(long id) {
        ratingRepository.deleteById(id);
    }


}


