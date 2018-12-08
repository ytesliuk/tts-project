package model.dao;

import model.entity.*;

import java.util.List;

/**
 * @author Yuliia Tesliuk
 */
public interface TaskDao extends GenericDao<Task>{


    List<Task> findActiveTasksByOwner(long userId);

    void createTaskUpdate(TaskUpdate record);
    void createTaskComment(Comment record);
    void createWatcher(Task task, User user);


    List<Task> findActiveTasksByCreator(long userId);

    List<Comment> findAllTaskComment(long id);
}
