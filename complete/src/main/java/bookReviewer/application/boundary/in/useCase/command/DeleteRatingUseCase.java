package bookReviewer.application.boundary.in.useCase.command;

import bookReviewer.application.useCase.command.deleteRatingUseCase.DeleteRatingCommand;

public interface DeleteRatingUseCase {
    void deleteRating(DeleteRatingCommand deleteRatingCommand);
}
