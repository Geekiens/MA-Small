package bookReviewer.adapter.in.web.rating;

import bookReviewer.adapter.in.web.util.TokenDecoder;
import bookReviewer.business.useCase.command.createRatingUseCase.CreateRatingCommand;
import bookReviewer.business.useCase.command.createRatingUseCase.Rating;

public final class CreateRatingCommandMapper {
    public static CreateRatingCommand map(Long bookId, NewRating newRating, String token) {
        CreateRatingCommand createRatingCommand = new CreateRatingCommand();
        createRatingCommand.setBookId(bookId);

        Long userId = TokenDecoder.getUserId(token);
        createRatingCommand.setUserId(userId);

        Rating rating = mapRating(newRating);
        createRatingCommand.setRating(rating);

        return createRatingCommand;

    }

    private static Rating mapRating(NewRating newRating){
        Rating rating = new Rating();
        rating.setContent(newRating.getContent());
        rating.setScore(newRating.getScore());
        rating.setTitle(newRating.getTitle());
        return rating;
    }
}
