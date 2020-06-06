package bookReviewer.application.useCase.query.getRatingsOfBookUseCase;

import bookReviewer.application.boundary.in.useCase.query.GetRatingsOfBookUseCase;
import bookReviewer.application.boundary.out.persistence.FindAllRatingsByBookId;
import bookReviewer.application.boundary.out.persistence.FindUserById;
import bookReviewer.entity.rating.Rating;
import bookReviewer.entity.user.User;

import java.util.ArrayList;
import java.util.List;

public class GetRatingsOfBookService implements GetRatingsOfBookUseCase {

    FindAllRatingsByBookId findAllRatingsByBookId;

    FindUserById findUserById;

    public GetRatingsOfBookService(FindAllRatingsByBookId findAllRatingsByBookId, FindUserById findUserById){
        this.findAllRatingsByBookId = findAllRatingsByBookId;
        this.findUserById = findUserById;
    }

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
