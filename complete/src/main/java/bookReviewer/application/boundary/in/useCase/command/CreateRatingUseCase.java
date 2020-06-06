package bookReviewer.application.boundary.in.useCase.command;

import bookReviewer.application.useCase.command.createRatingUseCase.CreateRatingCommand;

public interface CreateRatingUseCase {
    Long createRating(CreateRatingCommand createRatingCommand);
}
