package bookReviewer.application.useCase.command.registerUserUseCase;

import bookReviewer.application.shared.model.Role;

public class RegisterUserCommand {
    private String username;
    private String password;
    private String email;
    private Role role;
    private  String gender;

    public RegisterUserCommand() {
    }

    public RegisterUserCommand(String username, String password, String email, Role role, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
