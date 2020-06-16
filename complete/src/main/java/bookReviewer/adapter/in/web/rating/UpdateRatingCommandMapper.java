package bookReviewer.adapter.in.web.rating;

import bookReviewer.adapter.in.web.util.token.TokenDecoder;
import bookReviewer.application.useCase.command.updateRatingUseCase.Rating;
import bookReviewer.application.useCase.command.updateRatingUseCase.UpdateRatingCommand;

public class UpdateRatingCommandMapper {

    TokenDecoder tokenDecoder;

    public UpdateRatingCommandMapper(TokenDecoder tokenDecoder){
        this.tokenDecoder = tokenDecoder;
    }

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
