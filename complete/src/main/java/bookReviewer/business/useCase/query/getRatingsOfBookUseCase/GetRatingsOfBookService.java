package bookReviewer.business.useCase.query.getRatingsOfBookUseCase;

import bookReviewer.business.boundary.in.useCase.query.GetRatingsOfBookUseCase;
import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookId;
import bookReviewer.business.boundary.out.persistence.FindUserById;
import bookReviewer.entity.rating.Rating;
import bookReviewer.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public List<GetRatingsOutput> getRatingsOfBook(Long bookId) {
        List<Rating> ratings = findAllRatingsByBookId.findAllRatingsByBookId(bookId);
        List<GetRatingsOutput> outputRatings = new ArrayList<>();
        for (Rating rating : ratings) {
            User user = findUserById.findUserById(rating.getUserId()).orElse(null);
            GetRatingsOutput outputRating = RatingsOutputMapper.map(rating, user.getCredentials().getUsername());
            outputRatings.add(outputRating);
        }
        return outputRatings;
    }
}
