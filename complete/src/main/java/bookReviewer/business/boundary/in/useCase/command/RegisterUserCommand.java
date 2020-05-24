package bookReviewer.business.boundary.in.useCase.command;

import bookReviewer.business.model.Role;

public interface RegisterUserCommand {
    void registerUser(String username, String password, String email, Role role) throws Exception;
}
