package bookReviewer.adapter.in.web.rating;

import bookReviewer.application.useCase.query.getRatingsOfBookWithContentUseCase.GetRatingsWithCommentOutput;

import java.util.ArrayList;
import java.util.List;

public class RatingsWithCommentMapper {
    private static Rating map(GetRatingsWithCommentOutput ratingsOutput){
        Rating rating = new Rating();
        rating.setTitle(ratingsOutput.getTitle());
        rating.setAuthor(ratingsOutput.getAuthor());
        rating.setBookId(ratingsOutput.getBookId());
        rating.setContent(ratingsOutput.getContent());
        rating.setScore(ratingsOutput.getScore());
        rating.setUserId(ratingsOutput.getUserId());
        rating.setId(ratingsOutput.getId());
        return rating;
    }

    public static List<Rating> mapList(List<GetRatingsWithCommentOutput> ratingsOutputs){
        List<Rating> ratings = new ArrayList<>();
        for(GetRatingsWithCommentOutput rating : ratingsOutputs){
            ratings.add(map(rating));
        }
        return ratings;
    }
}
