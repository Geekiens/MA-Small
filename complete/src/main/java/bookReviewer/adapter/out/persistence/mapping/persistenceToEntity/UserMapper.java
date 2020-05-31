package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.entity.user.Credentials;
import bookReviewer.entity.user.User;
import bookReviewer.persistence.model.Activity;

import java.util.ArrayList;
import java.util.List;


public final class UserMapper {
    public static User map(bookReviewer.persistence.model.User userPersistence, List<Activity> activities){
        User user = new User();
        user.setId(userPersistence.getId());
        user.setEmail(userPersistence.getEmail());
        user.setRole(RoleMapper.map(userPersistence.getRole()));

        Credentials credentials = new Credentials();
        credentials.setPassword(userPersistence.getPassword());
        credentials.setSalt(userPersistence.getSalt());
        credentials.setUsername(userPersistence.getUsername());
        user.setCredentials(credentials);

        user.setActivities(ActivityMapper.mapList(activities));

        return user;
    }

    public  static ArrayList<User> mapList(List<bookReviewer.persistence.model.User> userPersistenceList){
        ArrayList<User> users = new ArrayList<>();
        for (bookReviewer.persistence.model.User userPersistence : userPersistenceList){
            users.add(map(userPersistence, null));
        }
        return users;
    }
}
