package bookReviewer.adapter.in.web.rating;

import bookReviewer.business.useCase.query.getRatingsOfBookUseCase.GetRatingsOutput;

import java.util.ArrayList;
import java.util.List;

public class RatingsMapper {
    private static Rating map(GetRatingsOutput ratingsOutput){
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

    public static List<Rating> mapList(List<GetRatingsOutput> ratingsOutputs){
        List<Rating> ratings = new ArrayList<>();
        for(GetRatingsOutput rating : ratingsOutputs){
            ratings.add(map(rating));
        }
        return ratings;
    }
}
