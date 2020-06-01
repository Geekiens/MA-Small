package bookReviewer.adapter.in.web.user;

import bookReviewer.business.shared.model.Role;

public interface UserAdapter {
    String loginUser(String username, String password);
    void registerUser (String username, String password, String email);
}
