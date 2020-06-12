package bookReviewer.adapter.in.web.rating;

import bookReviewer.business.useCase.query.getRatingsOfBookUseCase.GetRatingsOutput;

import java.util.ArrayList;
import java.util.List;

public class RatingsMapper {
    private static RatingDTO map(GetRatingsOutput ratingsOutput){
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setTitle(ratingsOutput.getTitle());
        ratingDTO.setAuthor(ratingsOutput.getAuthor());
        ratingDTO.setBookId(ratingsOutput.getBookId());
        ratingDTO.setContent(ratingsOutput.getContent());
        ratingDTO.setScore(ratingsOutput.getScore());
        ratingDTO.setUserId(ratingsOutput.getUserId());
        ratingDTO.setId(ratingsOutput.getId());
        return ratingDTO;
    }

    public static List<RatingDTO> mapList(List<GetRatingsOutput> ratingsOutputs){
        List<RatingDTO> ratingDTOS = new ArrayList<>();
        for(GetRatingsOutput rating : ratingsOutputs){
            ratingDTOS.add(map(rating));
        }
        return ratingDTOS;
    }
}
