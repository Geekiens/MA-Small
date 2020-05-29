package bookReviewer.entity.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class User {
    private long id;
    @Email
    private String email;
    @NotNull
    private Role role;
    @NotNull
    private Credentials credentials;
    private ArrayList<Activity> activities;

    public User() {
    }

    public User(String email, Role role, Credentials credentials) {
        this.email = email;
        this.role = role;
        this.credentials = credentials;
    }

    public User(long id, String email, Role role, Credentials credentials) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.credentials = credentials;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", credentials=" + credentials.toString() +
                '}';
    }
}
