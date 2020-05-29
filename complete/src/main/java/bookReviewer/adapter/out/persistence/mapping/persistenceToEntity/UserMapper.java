package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.entity.user.Credentials;
import bookReviewer.entity.user.User;


public final class UserMapper {
    public static User map(bookReviewer.persistence.model.User userPersistence){
        User user = new User();
        user.setId(userPersistence.getId());
        user.setEmail(userPersistence.getEmail());
        user.setRole(RoleMapper.map(userPersistence.getRole()));

        Credentials credentials = new Credentials();
        credentials.setPassword(userPersistence.getPassword());
        credentials.setSalt(userPersistence.getSalt());
        credentials.setUsername(userPersistence.getUsername());

        user.setCredentials(credentials);
        return user;
    }
}
