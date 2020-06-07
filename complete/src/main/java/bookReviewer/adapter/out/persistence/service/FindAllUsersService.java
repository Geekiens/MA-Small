package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.UserMapper;
import bookReviewer.application.boundary.out.persistence.FindAllUsers;
import bookReviewer.entity.user.User;
import bookReviewer.adapter.out.persistence.model.Activity;
import bookReviewer.adapter.out.persistence.repository.ActivityRepository;
import bookReviewer.adapter.out.persistence.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class FindAllUsersService implements FindAllUsers {

    ActivityRepository activityRepository;

    UserRepository userRepository;

    public FindAllUsersService(ActivityRepository activityRepository, UserRepository userRepository){
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

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
