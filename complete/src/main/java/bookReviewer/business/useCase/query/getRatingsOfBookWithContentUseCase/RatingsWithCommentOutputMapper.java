package bookReviewer.business.useCase.query.getRatingsOfBookWithContentUseCase;

import bookReviewer.entity.rating.Rating;

public class RatingsWithCommentOutputMapper {
    public GetRatingsWithCommentOutput map(Rating rating, String author){
        GetRatingsWithCommentOutput getRatingsOutput = new GetRatingsWithCommentOutput();
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
