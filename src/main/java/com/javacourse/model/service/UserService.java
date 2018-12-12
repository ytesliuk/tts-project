package com.javacourse.model.service;

import com.javacourse.model.dao.DaoFactory;
import com.javacourse.model.dao.TaskDao;
import com.javacourse.model.dao.UserDao;
import com.javacourse.model.entity.Task;
import com.javacourse.model.entity.User;

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
