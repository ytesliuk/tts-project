package model.dao;

import model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Yuliia Tesliuk
 */
public interface UserDao extends GenericDao<User>{

    User findByLoginAndPassword(String login, String password);

    boolean checkAccess(long taskId, long userId);

    List<User> findByLastName(String lastName, boolean partialMatch);

    List<User> findByDepartment(User.Department department);
}
