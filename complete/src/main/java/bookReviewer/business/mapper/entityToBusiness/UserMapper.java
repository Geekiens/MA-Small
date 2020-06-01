package bookReviewer.business.mapper.entityToBusiness;

import bookReviewer.business.model.UserBusiness;
import bookReviewer.business.shared.mapper.entityToBusiness.RoleMapper;
import bookReviewer.entity.user.User;

import java.util.ArrayList;
import java.util.List;

public final class UserMapper {
    public static UserBusiness map(User user){
        UserBusiness userBusiness = new UserBusiness();
        userBusiness.setEmail(user.getEmail());
        userBusiness.setId(user.getId());
        userBusiness.setRole(RoleMapper.map(user.getRole()));
        userBusiness.setPassword(user.getCredentials().getPassword());
        userBusiness.setUsername(user.getCredentials().getUsername());
        userBusiness.setSalt(user.getCredentials().getSalt());
        return userBusiness;
    }

    public static ArrayList<UserBusiness> mapList(List<User> users){
        ArrayList<UserBusiness> userBusinessList = new ArrayList<>();
        for (User user : users){
            userBusinessList.add(map(user));
        }
        return userBusinessList;
    }
}
