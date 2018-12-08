package model.service;

import model.dao.UserDao;
import model.dao.mysql.MySQLDaoFactory;
import model.dao.mysql.MySQLUserDao;
import model.entity.User;

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
        return userDao.checkAccess(taskId, userId);
    }
}
