package bookReviewer.business.useCase.query.getTokenByLoginUseCase;

import bookReviewer.business.shared.Role;

public class LoginOutput {
    Long userId;
    Role role;

    public LoginOutput() {
    }

    public LoginOutput(Long userId, Role role) {
        this.userId = userId;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
