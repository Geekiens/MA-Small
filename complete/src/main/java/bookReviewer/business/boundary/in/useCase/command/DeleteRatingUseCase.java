package bookReviewer.business.boundary.in.useCase.command;

import bookReviewer.business.useCase.command.deleteRatingUseCase.DeleteRatingCommand;

public interface DeleteRatingUseCase {
    void deleteRating(DeleteRatingCommand deleteRatingCommand);
}
