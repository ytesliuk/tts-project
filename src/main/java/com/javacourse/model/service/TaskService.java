package com.javacourse.model.service;

import com.javacourse.model.dao.DaoFactory;
import com.javacourse.model.dao.TaskDao;
import com.javacourse.model.dao.UserDao;
import com.javacourse.model.entity.*;

import java.util.List;

/**
 * @author Yuliia Tesliuk
 */
public class TaskService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public Task findTask(long taskID){
        try(TaskDao taskDao = daoFactory.createTaskDao()) {
            return taskDao.findByID(taskID);
        }
    }

    public void createTask(Task task){
        try(TaskDao taskDao = daoFactory.createTaskDao()) {
            taskDao.create(task);
        }
    }

    public void addWatcher(Task task, long userId){
        try(TaskDao taskDao = daoFactory.createTaskDao()) {
            taskDao.createWatcher(task, userId);
        }
    }

    public void saveTaskUpdate(TaskUpdate record){
        try(TaskDao taskDao = daoFactory.createTaskDao()) {
            taskDao.createTaskUpdate(record);
        }
    }

    public void saveTaskComment(TaskComment comment){
        try(TaskDao taskDao = daoFactory.createTaskDao()) {
            taskDao.createTaskComment(comment);
        }
    }


    public List<TaskComment> getTaskComments(Task task) {
        try(TaskDao taskDao = daoFactory.createTaskDao()) {
            return taskDao.findAllTaskComment(task.getId());
        }
    }

    public List<User> getUsersByName(String lastName, boolean partialMatch){
        try(UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByLastName(lastName, partialMatch);
        }
    }
    public List<User> getUsersByDepartment(User.Department department){
        try(UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByDepartment(department);
        }
    }
}
