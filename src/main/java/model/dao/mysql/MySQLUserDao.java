package model.dao.mysql;

import controller.util.PropertiesLoader;
import model.dao.UserDao;
import model.dao.mapper.UserMapper;
import model.entity.User;
import model.service.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Yuliia Tesliuk
 */
public class MySQLUserDao implements UserDao {
    private Connection connection;
    private Properties properties = new PropertiesLoader().getLoadedProperties("mysql_requests.properties");

    public MySQLUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(properties.getProperty("createUser"), Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getDepartment().toString());
            statement.setString(4, entity.getPosition());
            statement.setString(5, entity.getEmail());
            statement.setString(6, entity.getRole().toString());
            statement.setString(7, entity.getLogin());
            statement.setString(8, entity.getPassword());

            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                entity.setId(rs.getInt("GENERATED_KEY"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByID(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(properties.getProperty("findUserByID"));
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return new UserMapper().mapping(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;  //TODO change
    }


    public User findByLoginAndPassword(String login, String password){
        try {
            PreparedStatement statement = connection.prepareStatement(properties.getProperty("findUserByLoginAndPassword"));
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return new UserMapper().mapping(rs);
            } else{
                throw new UserNotFoundException("Wrong login or password. Please try again");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(properties.getProperty("findAllUsers"));
            UserMapper mapper = new UserMapper();
            if(rs.next()){
                users.add(mapper.mapping(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void update(User entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(properties.getProperty("updateUser"));
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getDepartment().toString());
            statement.setString(4, entity.getPosition());
            statement.setString(5, entity.getEmail());
            statement.setString(6, entity.getRole().toString());
            statement.setLong(7, entity.getId());

            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(properties.getProperty("deleteUser"));
            statement.setLong(1, id);

            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkAccess(long taskId, long userId){
        try {
            PreparedStatement statement = connection.prepareStatement(properties.getProperty("checkTaskAccess"));
            statement.setLong(1, taskId);
            statement.setLong(2, userId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }
}
