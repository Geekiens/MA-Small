package bookReviewer.business.useCase.query.getRatingsOfBookUseCase;

import bookReviewer.business.boundary.in.useCase.query.GetRatingsOfBookUseCase;
import bookReviewer.business.mapper.RatingBusinessMapper;
import bookReviewer.business.model.RatingBusiness;
import bookReviewer.persistence.model.Rating;
import bookReviewer.persistence.model.User;
import bookReviewer.persistence.repository.RatingRepository;
import bookReviewer.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("GetRatingsOfBookService")
public class GetRatingsOfBookService implements GetRatingsOfBookUseCase {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    public List<RatingBusiness> getRatingsOfBook(Long bookId) {
        List<Rating> ratings = ratingRepository.findAllByBookId(bookId);

        for (int i = 0; i < ratings.size(); i++) {
            User user = userRepository.findById(ratings.get(i).getUserId()).orElse(null);
            if (user != null) {
                ratings.get(i).setAuthor(user.getUsername());
            }
        }
        return RatingBusinessMapper.ratingBusinessList(ratings);
    }
}
