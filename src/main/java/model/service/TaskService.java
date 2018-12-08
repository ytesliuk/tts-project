package model.service;

import model.dao.DaoFactory;
import model.dao.TaskDao;
import model.entity.*;

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

    public void addWatcher(Task task, User user){
        try(TaskDao taskDao = daoFactory.createTaskDao()) {
            taskDao.createWatcher(task, user);
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
}
