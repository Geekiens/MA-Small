package bookReviewer.adapter.out.persistence.mapping.persistenceToEntity;

import bookReviewer.entity.user.Credentials;
import bookReviewer.entity.user.User;
import bookReviewer.adapter.out.persistence.model.Activity;

import java.util.ArrayList;
import java.util.List;


public final class UserMapper {
    public static User map(bookReviewer.adapter.out.persistence.model.User userPersistence, List<Activity> activities){
        User user = new User();
        user.setId(userPersistence.getId());
        user.setEmail(userPersistence.getEmail());
        user.setRole(RoleMapper.map(userPersistence.getRole()));
        user.setGender(userPersistence.getGender());

        Credentials credentials = new Credentials();
        credentials.setPassword(userPersistence.getPassword());
        credentials.setSalt(userPersistence.getSalt());
        credentials.setUsername(userPersistence.getUsername());
        user.setCredentials(credentials);

        user.setActivities(ActivityMapper.mapList(activities));

        return user;
    }

    public  static ArrayList<User> mapList(List<bookReviewer.adapter.out.persistence.model.User> userPersistenceList){
        ArrayList<User> users = new ArrayList<>();
        for (bookReviewer.adapter.out.persistence.model.User userPersistence : userPersistenceList){
            users.add(map(userPersistence, null));
        }
        return users;
    }
}
