package bookReviewer.business.boundary.in.useCase.command;

import bookReviewer.business.useCase.command.registerUserUseCase.RegisterUserCommand;

public interface RegisterUserUseCase {
    void registerUser(RegisterUserCommand registerUserCommand) throws Exception;
}
