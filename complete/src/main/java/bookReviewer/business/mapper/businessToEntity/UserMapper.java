package bookReviewer.business.mapper.businessToEntity;

import bookReviewer.business.model.UserBusiness;
import bookReviewer.business.shared.mapper.businessToEntity.RoleMapper;
import bookReviewer.entity.user.Activity;
import bookReviewer.entity.user.Credentials;
import bookReviewer.entity.user.User;

import java.util.ArrayList;

public final class UserMapper {
    public static User map (UserBusiness userBusiness, ArrayList<Activity> activities){
        User user = new User();
        user.setRole(RoleMapper.map(userBusiness.getRole()));
        user.setEmail(userBusiness.getEmail());
        user.setId(userBusiness.getId());
        user.setActivities(activities);
        Credentials credentials = new Credentials();
        credentials.setUsername(userBusiness.getUsername());
        credentials.setPassword(userBusiness.getPassword());
        credentials.setSalt(userBusiness.getSalt());
        user.setCredentials(credentials);
        return user;
    }

}
