package bookReviewer.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="username")
    @NotBlank
    private String username;

    @Column(name="password")
    @NotNull
    private String password;

    @Column(name="email")
    @Email
    private String email;

    @Column(name="salt")
    private byte[] salt;

    @Column(name="role")
    @NotNull
    private Role role;

    @Column(name="gender")
    private String gender;

    public User() {
    }

    public User(String username, String password, String email, byte[] salt, Role role, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.salt = salt;
        this.role = role;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
