package bookReviewer.adapter.in.web.rating;

import bookReviewer.adapter.in.web.util.token.TokenDecoder;
import bookReviewer.application.useCase.command.createRatingUseCase.CreateRatingCommand;
import bookReviewer.application.useCase.command.createRatingUseCase.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateRatingCommandMapper {
    @Autowired
    TokenDecoder tokenDecoder;
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
