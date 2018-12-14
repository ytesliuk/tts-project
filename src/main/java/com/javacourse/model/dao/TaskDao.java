package com.javacourse.model.dao;

import com.javacourse.model.entity.*;

import java.util.List;

/**
 * @author Yuliia Tesliuk
 */
public interface TaskDao extends GenericDao<Task>{


    List<Task> findActiveTasksByOwner(long userId);

    void createTaskUpdate(TaskUpdate record);
    void createTaskComment(TaskComment record);
    void createWatcher(Task task, long userId);


    List<Task> findActiveTasksByCreator(long userId);

    List<TaskComment> findAllTaskComment(long id);
}
