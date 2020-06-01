package bookReviewer.business.useCase.query.getRatingsOfBookUseCase;

import bookReviewer.entity.rating.Rating;

public final class RatingsOutputMapper {
    public static GetRatingsOutput map(Rating rating, String author){
        GetRatingsOutput getRatingsOutput = new GetRatingsOutput();
        getRatingsOutput.setContent(rating.getRatingDetails().getContent());
        getRatingsOutput.setTitle(rating.getRatingDetails().getTitle());
        getRatingsOutput.setId(rating.getId());
        getRatingsOutput.setScore(rating.getRatingDetails().getScore());
        getRatingsOutput.setUserId(rating.getUserId());
        getRatingsOutput.setBookId(rating.getBookId());
        getRatingsOutput.setAuthor(author);
        return getRatingsOutput;
    }
}
