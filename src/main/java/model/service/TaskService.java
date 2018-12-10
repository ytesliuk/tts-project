package model.service;

import model.dao.DaoFactory;
import model.dao.TaskDao;
import model.dao.UserDao;
import model.entity.*;

import java.util.List;
import java.util.Optional;

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

    public void saveTaskComment(Comment comment){
        try(TaskDao taskDao = daoFactory.createTaskDao()) {
            taskDao.createTaskComment(comment);
        }
    }


    public List<Comment> getTaskComments(Task task) {
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
