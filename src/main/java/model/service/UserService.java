package model.service;

import model.dao.DaoFactory;
import model.dao.TaskDao;
import model.dao.UserDao;
import model.entity.Task;
import model.entity.User;

import java.util.List;

/**
 * @author Yuliia Tesliuk
 */
public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public User findUser(long userID){
        try(UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByID(userID);
        }
    }

    public void createUser(User user){
        try(UserDao userDao = daoFactory.createUserDao()) {
            userDao.create(user);
        }
    }
//    public List<User> findTaskWatchers(long taskID){
//        try(UserDao userDao = daoFactory.createUserDao()) {
//            return userDao.findTaskWatchers(taskID);
//        }
//    }

    public List<Task> getActiveTasksByCreator(long userId){
        try(TaskDao taskDao = daoFactory.createTaskDao()) {
            return taskDao.findActiveTasksByCreator(userId);
        }
    }

    public List<Task> getActiveTasksByOwner(long userId){
        try(TaskDao taskDao = daoFactory.createTaskDao()) {
            return taskDao.findActiveTasksByOwner(userId);
        }
    }

}
