package model.dao.mysql;

import model.dao.TaskDao;
import model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Yuliia Tesliuk
 */
public class MySQLDaoFactory extends model.dao.DaoFactory {

    private DataSource dataSource = ConnectionPool.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new MySQLUserDao(getConnection());
    }

    @Override
    public TaskDao createTaskDao() {
        return new MySQLTaskDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
