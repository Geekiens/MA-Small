package bookReviewer.adapter.in.web.user;

public interface UserAdapter {
    String loginUser(String username, String password);
    void registerUser (String username, String password, String email, String gender);
}
