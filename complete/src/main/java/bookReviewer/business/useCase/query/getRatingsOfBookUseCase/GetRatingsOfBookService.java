package bookReviewer.business.useCase.query.getRatingsOfBookUseCase;

import bookReviewer.business.boundary.in.useCase.query.GetRatingsOfBookUseCase;
import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookId;
import bookReviewer.business.boundary.out.persistence.FindUserById;
import bookReviewer.business.mapper.RatingBusinessMapper;
import bookReviewer.business.model.RatingBusiness;
import bookReviewer.persistence.model.Rating;
import bookReviewer.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("GetRatingsOfBookService")
public class GetRatingsOfBookService implements GetRatingsOfBookUseCase {

    @Autowired
    @Qualifier("FindAllRatingsByBookIdService")
    FindAllRatingsByBookId findAllRatingsByBookId;

    @Autowired
    @Qualifier("FindUserByIdService")
    FindUserById findUserById;

    public List<RatingBusiness> getRatingsOfBook(Long bookId) {
        List<Rating> ratings = findAllRatingsByBookId.findAllRatingsByBookId(bookId);

        for (int i = 0; i < ratings.size(); i++) {
            User user = findUserById.findUserById(ratings.get(i).getUserId()).orElse(null);
            if (user != null) {
                ratings.get(i).setAuthor(user.getUsername());
            }
        }
        return RatingBusinessMapper.ratingBusinessList(ratings);
    }
}
