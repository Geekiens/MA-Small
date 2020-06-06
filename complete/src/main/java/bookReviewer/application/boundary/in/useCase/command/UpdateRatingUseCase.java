package bookReviewer.application.boundary.in.useCase.command;

import bookReviewer.application.useCase.command.updateRatingUseCase.UpdateRatingCommand;

public interface UpdateRatingUseCase {
    void updateRating(UpdateRatingCommand updateRatingCommand);
}
