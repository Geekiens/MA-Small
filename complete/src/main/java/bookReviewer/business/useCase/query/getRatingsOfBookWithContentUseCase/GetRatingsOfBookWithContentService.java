package bookReviewer.business.useCase.query.getRatingsOfBookWithContentUseCase;

import bookReviewer.business.boundary.in.useCase.query.GetRatingsOfBookWithContentUseCase;
import bookReviewer.business.boundary.out.persistence.FindAllRatingsByBookIdWithContent;
import bookReviewer.business.mapper.entityToBusiness.RatingMapper;
import bookReviewer.business.model.RatingBusiness;
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

    RatingMapper ratingMapper;

    public List<RatingBusiness> getRatingsOfBookWithContent(Long bookId) {
        List<RatingBusiness> ratings = ratingMapper.mapList(findAllRatingsByBookIdWithContent.findAllRatingsByBookIdWithContent(bookId));
        return ratings;
    }
}