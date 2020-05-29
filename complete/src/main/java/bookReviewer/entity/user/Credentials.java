package bookReviewer.entity.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Credentials {
    @NotBlank
    private String username;
    @NotNull
    private String password;
    private byte[] salt;

    public Credentials(String username, String password, byte[] salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    public Credentials() {
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

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt.toString() + '\'' +
                '}';
    }
}
