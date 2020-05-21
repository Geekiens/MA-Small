package bookReviewer.business.model;

public class UserBusiness {
    private long id;
    private String username;
    private String password;
    private String email;
    private byte[] salt;
    private Role role;

    public UserBusiness() {
    }

    public UserBusiness(String username, String password, String email, byte[] salt, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.salt = salt;
        this.role = role;
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

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
