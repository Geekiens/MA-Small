package bookReviewer.business.mapper;

import bookReviewer.business.model.UserBusiness;
import bookReviewer.persistence.model.Role;
import bookReviewer.persistence.model.User;

public final class UserBusinessMapper {


    public static User user(UserBusiness userBusiness) {
        User user = new User();
        user.setId(userBusiness.getId());
        user.setPassword(userBusiness.getPassword());
        user.setUsername(userBusiness.getUsername());
        user.setEmail(userBusiness.getEmail());
        user.setSalt(userBusiness.getSalt());
        user.setRole(RoleMapper.role(userBusiness.getRole()));
        return user;
    }

    public static UserBusiness userBusiness(User user) {
        UserBusiness userBusiness = new UserBusiness();
        userBusiness.setId(user.getId());
        userBusiness.setUsername(user.getUsername());
        userBusiness.setPassword(user.getPassword());
        userBusiness.setEmail(user.getEmail());
        userBusiness.setSalt(user.getSalt());
        userBusiness.setRole(RoleMapper.roleBusiness(user.getRole()));
        return userBusiness;
    }
}
