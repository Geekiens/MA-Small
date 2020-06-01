package bookReviewer.business.boundary.in.useCase.command;

import bookReviewer.business.useCase.command.updateRatingUseCase.UpdateRatingCommand;

public interface UpdateRatingUseCase {
    void updateRating(UpdateRatingCommand updateRatingCommand);
}
