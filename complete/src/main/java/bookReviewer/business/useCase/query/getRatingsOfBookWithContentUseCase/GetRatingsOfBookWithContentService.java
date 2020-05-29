package bookReviewer.business.useCase.query.getRatingsOfBookWithContentUseCase;

import bookReviewer.business.boundary.in.useCase.query.GetRatingsOfBookWithContentUseCase;
import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookIdWithContent;
import bookReviewer.business.mapper.RatingBusinessMapper;
import bookReviewer.business.model.RatingBusiness;
import bookReviewer.persistence.model.Rating;
import bookReviewer.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("GetRatingsOfBookWithContentService")
public class GetRatingsOfBookWithContentService implements GetRatingsOfBookWithContentUseCase {

    @Autowired
    @Qualifier("FindAllRatingsByBookIdWithContentService")
    FindAllRatingsByBookIdWithContent findAllRatingsByBookIdWithContent;

    public List<RatingBusiness> getRatingsOfBookWithContent(Long bookId) {
        List<Rating> ratings = findAllRatingsByBookIdWithContent.findAllRatingsByBookIdWithContent(bookId);
        return RatingBusinessMapper.ratingBusinessList(ratings);
    }
}
