package bookReviewer.application.boundary.in.useCase.command;

import bookReviewer.application.useCase.command.registerUserUseCase.RegisterUserCommand;

public interface RegisterUserUseCase {
    void registerUser(RegisterUserCommand registerUserCommand) throws Exception;
}
