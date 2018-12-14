package com.javacourse.model.dao;

import com.javacourse.model.entity.User;

import java.util.List;

/**
 * @author Yuliia Tesliuk
 */
public interface UserDao extends GenericDao<User>{

    User findByLoginAndPassword(String login, String password);

    List<Long> checkAccess(long taskId);

    List<User> findByLastName(String lastName, boolean partialMatch);

    List<User> findByDepartment(User.Department department);
}
