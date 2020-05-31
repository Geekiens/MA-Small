package bookReviewer.business.boundary.in.useCase.command;

import bookReviewer.business.shared.model.Role;

public interface RegisterUserUseCase {
    void registerUser(String username, String password, String email, Role role) throws Exception;
}
