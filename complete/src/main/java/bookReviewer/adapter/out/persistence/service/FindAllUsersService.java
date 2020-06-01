package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.UserMapper;
import bookReviewer.business.boundary.out.persistence.FindAllUsers;
import bookReviewer.entity.user.User;
import bookReviewer.adapter.out.persistence.model.Activity;
import bookReviewer.adapter.out.persistence.repository.ActivityRepository;
import bookReviewer.adapter.out.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("FindAllUsersService")
public class FindAllUsersService implements FindAllUsers {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    public List<User> findAllUsers(){
        List<bookReviewer.adapter.out.persistence.model.User> userList = userRepository.findAll();
        ArrayList<User> users = new ArrayList<>();
        for (bookReviewer.adapter.out.persistence.model.User userPersistence : userList){
            List<Activity> activities = activityRepository.findAllByUser(userPersistence);
            users.add(UserMapper.map(userPersistence, activities));
        }
        return users;
    }
}
