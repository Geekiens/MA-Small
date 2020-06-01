package bookReviewer.business.useCase.query.getTokenByLoginUseCase;

import bookReviewer.business.shared.model.Role;

public class LoginOutput {
    private Long userId;
    private String username;
    private Role role;

    public LoginOutput() {
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
