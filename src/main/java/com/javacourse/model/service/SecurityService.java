package com.javacourse.model.service;

import com.javacourse.model.dao.UserDao;
import com.javacourse.model.dao.mysql.MySQLDaoFactory;
import com.javacourse.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yuliia Tesliuk
 */
public class SecurityService {

    public User userValidation(String login, String password) {
        UserDao userDao = new MySQLDaoFactory().createUserDao();
        return userDao.findByLoginAndPassword(login, password);
    }

    public boolean checkTaskAccess(long taskId, long userId) {
        UserDao userDao = new MySQLDaoFactory().createUserDao();
        return userDao.checkAccess(taskId).contains(userId);
    }
}
