package model.dao;

import model.entity.User;

/**
 * @author Yuliia Tesliuk
 */
public interface UserDao extends GenericDao<User>{

    User findByLoginAndPassword(String login, String password);

    boolean checkAccess(long taskId, long userId);
}
