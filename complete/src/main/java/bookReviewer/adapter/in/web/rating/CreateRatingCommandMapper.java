package bookReviewer.adapter.in.web.rating;

import bookReviewer.adapter.in.web.util.TokenDecoder;
import bookReviewer.business.useCase.command.createRatingUseCase.CreateRatingCommand;
import bookReviewer.business.useCase.command.createRatingUseCase.Rating;

public final class CreateRatingCommandMapper {
    public static CreateRatingCommand map(Long bookId, NewRatingDTO newRatingDTO, String token) {
        CreateRatingCommand createRatingCommand = new CreateRatingCommand();
        createRatingCommand.setBookId(bookId);

        Long userId = TokenDecoder.getUserId(token);
        createRatingCommand.setUserId(userId);

        Rating rating = mapRating(newRatingDTO);
        createRatingCommand.setRating(rating);

        return createRatingCommand;

    }

    private static Rating mapRating(NewRatingDTO newRatingDTO){
        Rating rating = new Rating();
        rating.setContent(newRatingDTO.getContent());
        rating.setScore(newRatingDTO.getScore());
        rating.setTitle(newRatingDTO.getTitle());
        return rating;
    }
}
