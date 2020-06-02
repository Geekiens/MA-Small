package bookReviewer.adapter.in.web.rating;

import bookReviewer.adapter.in.web.util.token.TokenDecoder;
import bookReviewer.business.useCase.command.updateRatingUseCase.Rating;
import bookReviewer.business.useCase.command.updateRatingUseCase.UpdateRatingCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateRatingCommandMapper {

    @Autowired
    TokenDecoder tokenDecoder;

    public UpdateRatingCommand map(Long bookId, UpdateRating updateRating, String token){
        UpdateRatingCommand updateRatingCommand = new UpdateRatingCommand();
        updateRatingCommand.setBookId(bookId);

        Long userId = tokenDecoder.getUserId(token);
        updateRatingCommand.setUserId(userId);

        updateRatingCommand.setRating(mapRating(updateRating));

        return updateRatingCommand;
    }

    private static Rating mapRating(UpdateRating updateRating){
        Rating rating = new Rating();
        rating.setId(updateRating.getId());
        rating.setContent(updateRating.getContent());
        rating.setScore(updateRating.getScore());
        rating.setTitle(updateRating.getTitle());
        return rating;
    }

}
