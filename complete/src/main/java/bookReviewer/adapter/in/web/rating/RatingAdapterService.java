package bookReviewer.adapter.in.web.rating;

import bookReviewer.application.boundary.in.useCase.command.CreateRatingUseCase;
import bookReviewer.application.boundary.in.useCase.command.DeleteRatingUseCase;
import bookReviewer.application.boundary.in.useCase.command.UpdateRatingUseCase;
import bookReviewer.application.boundary.in.useCase.query.GetRatingsOfBookUseCase;
import bookReviewer.application.boundary.in.useCase.query.GetRatingsOfBookWithContentUseCase;

import java.util.List;

public class RatingAdapterService implements RatingAdapter {

    CreateRatingUseCase createRatingUseCase;

    DeleteRatingUseCase deleteRatingUseCase;

    UpdateRatingUseCase updateRatingUseCase;

    GetRatingsOfBookUseCase getRatingsOfBookUseCase;

    GetRatingsOfBookWithContentUseCase getRatingsOfBookWithContentUseCase;

    CreateRatingCommandMapper createRatingCommandMapper;

    DeleteRatingCommandMapper deleteRatingCommandMapper;

    UpdateRatingCommandMapper updateRatingCommandMapper;

    public RatingAdapterService(CreateRatingUseCase createRatingUseCase,
                                DeleteRatingUseCase deleteRatingUseCase,
                                UpdateRatingUseCase updateRatingUseCase,
                                GetRatingsOfBookUseCase getRatingsOfBookUseCase,
                                GetRatingsOfBookWithContentUseCase getRatingsOfBookWithContentUseCase,
                                CreateRatingCommandMapper createRatingCommandMapper,
                                DeleteRatingCommandMapper deleteRatingCommandMapper,
                                UpdateRatingCommandMapper updateRatingCommandMapper){
        this.createRatingUseCase = createRatingUseCase;
        this.deleteRatingUseCase = deleteRatingUseCase;
        this.updateRatingUseCase = updateRatingUseCase;
        this.getRatingsOfBookUseCase = getRatingsOfBookUseCase;
        this.getRatingsOfBookWithContentUseCase = getRatingsOfBookWithContentUseCase;
        this.createRatingCommandMapper = createRatingCommandMapper;
        this.deleteRatingCommandMapper = deleteRatingCommandMapper;
        this.updateRatingCommandMapper = updateRatingCommandMapper;
    }


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
