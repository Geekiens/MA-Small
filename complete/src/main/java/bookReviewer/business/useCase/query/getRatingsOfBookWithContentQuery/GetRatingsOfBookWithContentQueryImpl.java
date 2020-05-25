package bookReviewer.business.useCase.query.getRatingsOfBookWithContentQuery;

import bookReviewer.business.boundary.in.useCase.query.GetRatingsOfBookWithContentQuery;
import bookReviewer.business.mapper.RatingBusinessMapper;
import bookReviewer.business.model.RatingBusiness;
import bookReviewer.persistence.model.Rating;
import bookReviewer.persistence.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("GetRatingsOfBookWithContentQueryImpl")
public class GetRatingsOfBookWithContentQueryImpl implements GetRatingsOfBookWithContentQuery {

    @Autowired
    RatingRepository ratingRepository;

    public List<RatingBusiness> getRatingsOfBookWithContent(Long bookId) {
        List<Rating> ratings = ratingRepository.findAllByBookIdAndContentNotNull(bookId);
        return RatingBusinessMapper.ratingBusinessList(ratings);
    }
}
