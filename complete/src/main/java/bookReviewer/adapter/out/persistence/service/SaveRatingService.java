package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.entityToPersistence.RatingMapper;
import bookReviewer.application.boundary.out.persistence.SaveRating;
import bookReviewer.entity.rating.Rating;
import bookReviewer.adapter.out.persistence.model.Book;
import bookReviewer.adapter.out.persistence.model.User;
import bookReviewer.adapter.out.persistence.repository.BookRepository;
import bookReviewer.adapter.out.persistence.repository.RatingRepository;
import bookReviewer.adapter.out.persistence.repository.UserRepository;

public class SaveRatingService implements SaveRating {

    BookRepository bookRepository;

    RatingRepository ratingRepository;

    UserRepository userRepository;


    public SaveRatingService(BookRepository bookRepository, RatingRepository ratingRepository, UserRepository userRepository){
        this.bookRepository = bookRepository;
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    public Long saveRating(Rating rating){
        User user = userRepository.findById(rating.getUserId()).orElse(null);
        String author = null;
        if (user != null){
            author = user.getUsername();
        }
        Book book = bookRepository.findById(rating.getBookId()).orElse(null);
        return ratingRepository.saveAndFlush(RatingMapper.map(rating, book, author));
    }
}
