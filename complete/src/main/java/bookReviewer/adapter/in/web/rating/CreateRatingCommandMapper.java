package bookReviewer.adapter.in.web.rating;

import bookReviewer.adapter.in.web.util.token.TokenDecoder;
import bookReviewer.application.useCase.command.createRatingUseCase.CreateRatingCommand;
import bookReviewer.application.useCase.command.createRatingUseCase.Rating;

public class CreateRatingCommandMapper {

    TokenDecoder tokenDecoder;

    public CreateRatingCommandMapper(TokenDecoder tokenDecoder){
        this.tokenDecoder = tokenDecoder;
    }

    public CreateRatingCommand map(Long bookId, NewRating newRating, String token) {
        CreateRatingCommand createRatingCommand = new CreateRatingCommand();
        createRatingCommand.setBookId(bookId);

        Long userId = tokenDecoder.getUserId(token);
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
