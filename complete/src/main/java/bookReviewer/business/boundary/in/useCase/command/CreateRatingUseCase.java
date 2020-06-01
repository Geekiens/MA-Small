package bookReviewer.business.boundary.in.useCase.command;

import bookReviewer.business.useCase.command.createRatingUseCase.CreateRatingCommand;

public interface CreateRatingUseCase {
    Long createRating(CreateRatingCommand createRatingCommand);
}
