package bookReviewer.adapter.out.persistence.mapping.entityToPersistence;

import bookReviewer.adapter.out.persistence.model.User;

public class UserMapper {
    public static User map(bookReviewer.entity.user.User user){
        User userPersistence = new User();
        userPersistence.setId(user.getId());
        userPersistence.setUsername(user.getCredentials().getUsername());
        userPersistence.setPassword(user.getCredentials().getPassword());
        userPersistence.setSalt(user.getCredentials().getSalt());
        userPersistence.setEmail(user.getEmail());
        userPersistence.setRole(RoleMapper.map(user.getRole()));
        return userPersistence;
    }
}
