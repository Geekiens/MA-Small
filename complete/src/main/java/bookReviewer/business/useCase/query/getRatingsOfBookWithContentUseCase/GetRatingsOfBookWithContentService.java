package bookReviewer.business.useCase.query.getRatingsOfBookWithContentUseCase;

import bookReviewer.business.boundary.in.useCase.query.GetRatingsOfBookWithContentUseCase;
import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookIdWithContent;
import bookReviewer.business.boundary.out.persistence.FindUserById;
import bookReviewer.business.mapper.entityToBusiness.RatingMapper;
import bookReviewer.business.model.RatingBusiness;
import bookReviewer.entity.rating.Rating;
import bookReviewer.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("GetRatingsOfBookWithContentService")
public class GetRatingsOfBookWithContentService implements GetRatingsOfBookWithContentUseCase {

    @Autowired
    @Qualifier("FindAllRatingsByBookIdWithContentService")
    FindAllRatingsByBookIdWithContent findAllRatingsByBookIdWithContent;

    @Autowired
    @Qualifier("FindUserByIdService")
    FindUserById findUserById;

    @Autowired
    RatingMapper ratingMapper;

    public List<GetRatingsWithCommentOutput> getRatingsOfBookWithContent(Long bookId) {
        List<Rating> ratings = findAllRatingsByBookIdWithContent.findAllRatingsByBookIdWithContent(bookId);
        List<GetRatingsWithCommentOutput> outputRatings = new ArrayList<>();
        for (Rating rating : ratings) {
            User user = findUserById.findUserById(rating.getUserId()).orElse(null);
            GetRatingsWithCommentOutput outputRating = RatingsWithCommentOutputMapper.map(rating, user.getCredentials().getUsername());
            outputRatings.add(outputRating);
        }
        return outputRatings;
    }
}