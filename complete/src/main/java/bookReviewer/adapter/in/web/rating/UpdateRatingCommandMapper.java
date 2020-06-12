package bookReviewer.adapter.in.web.rating;

import bookReviewer.adapter.in.web.util.TokenDecoder;
import bookReviewer.business.useCase.command.updateRatingUseCase.Rating;
import bookReviewer.business.useCase.command.updateRatingUseCase.UpdateRatingCommand;

public class UpdateRatingCommandMapper {
    public static UpdateRatingCommand map(Long bookId, UpdateRatingDTO updateRatingDTO, String token){
        UpdateRatingCommand updateRatingCommand = new UpdateRatingCommand();
        updateRatingCommand.setBookId(bookId);

        Long userId = TokenDecoder.getUserId(token);
        updateRatingCommand.setUserId(userId);

        updateRatingCommand.setRating(mapRating(updateRatingDTO));

        return updateRatingCommand;
    }

    private static Rating mapRating(UpdateRatingDTO updateRatingDTO){
        Rating rating = new Rating();
        rating.setId(updateRatingDTO.getId());
        rating.setContent(updateRatingDTO.getContent());
        rating.setScore(updateRatingDTO.getScore());
        rating.setTitle(updateRatingDTO.getTitle());
        return rating;
    }

}
