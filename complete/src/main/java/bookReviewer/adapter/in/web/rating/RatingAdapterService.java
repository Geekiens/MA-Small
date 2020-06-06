package bookReviewer.adapter.in.web.rating;

import bookReviewer.application.boundary.in.useCase.command.CreateRatingUseCase;
import bookReviewer.application.boundary.in.useCase.command.DeleteRatingUseCase;
import bookReviewer.application.boundary.in.useCase.command.UpdateRatingUseCase;
import bookReviewer.application.boundary.in.useCase.query.GetRatingsOfBookUseCase;
import bookReviewer.application.boundary.in.useCase.query.GetRatingsOfBookWithContentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("RatingAdapterService")
public class RatingAdapterService implements RatingAdapter {

    @Autowired
    @Qualifier("CreateRatingService")
    CreateRatingUseCase createRatingUseCase;

    @Autowired
    @Qualifier("DeleteRatingService")
    DeleteRatingUseCase deleteRatingUseCase;

    @Autowired
    @Qualifier("UpdateRatingService")
    UpdateRatingUseCase updateRatingUseCase;

    @Autowired
    @Qualifier("GetRatingsOfBookService")
    GetRatingsOfBookUseCase getRatingsOfBookUseCase;

    @Autowired
    @Qualifier("GetRatingsOfBookWithContentService")
    GetRatingsOfBookWithContentUseCase getRatingsOfBookWithContentUseCase;

    @Autowired
    CreateRatingCommandMapper createRatingCommandMapper;

    @Autowired
    DeleteRatingCommandMapper deleteRatingCommandMapper;

    @Autowired
    UpdateRatingCommandMapper updateRatingCommandMapper;


    public Long createRating(Long bookId, NewRating newRating, String token){
        return createRatingUseCase.createRating(createRatingCommandMapper.map(bookId, newRating, token));
    }

    public  void  deleteRating(Long ratingId, String token){
        deleteRatingUseCase.deleteRating(deleteRatingCommandMapper.map(ratingId, token));
    }

    public void updateRating(Long bookId, UpdateRating rating, String token){
        updateRatingUseCase.updateRating(updateRatingCommandMapper.map(bookId, rating, token));
    }

    public List<Rating> getRatingsOfBook(Long bookId){
        return RatingsMapper.mapList(getRatingsOfBookUseCase.getRatingsOfBook(bookId));
    }

    public List<Rating> getRatingsOfBookWithContent(Long bookId){
        return RatingsWithCommentMapper.mapList(getRatingsOfBookWithContentUseCase.getRatingsOfBookWithContent(bookId));
    }

}
