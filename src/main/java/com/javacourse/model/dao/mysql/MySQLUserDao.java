package com.javacourse.model.dao.mysql;

import com.javacourse.controller.util.PropertiesLoader;
import com.javacourse.model.dao.UncheckedSQLException;
import com.javacourse.model.dao.UserDao;
import com.javacourse.model.dao.mapper.UserMapper;
import com.javacourse.model.entity.User;
import com.javacourse.model.service.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

/**
 * @author Yuliia Tesliuk
 */
public class MySQLUserDao implements UserDao {
    private Connection connection;
    private Properties properties = new PropertiesLoader().getLoadedProperties("mysql_requests.properties");
    private Logger logger = LoggerFactory.getLogger(MySQLUserDao.class);

    public MySQLUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("createUser"), Statement.RETURN_GENERATED_KEYS)) {
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
                entity.setId(rs.getLong("GENERATED_KEY"));
            }
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }

    @Override
    public User findByID(long id) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("findUserByID"))){
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return new UserMapper().mapping(rs);
            }
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
        return null;  //TODO change
    }


    public User findByLoginAndPassword(String login, String password){
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("findUserByLoginAndPassword"))){
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return new UserMapper().mapping(rs);
            } else{
                throw new UserNotFoundException("Wrong login or password. Please try again");
            }
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(properties.getProperty("findAllUsers"));
            if(rs.next()){
                users.add(new UserMapper().mapping(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void update(User entity) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("updateUser"))){
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getDepartment().toString());
            statement.setString(4, entity.getPosition());
            statement.setString(5, entity.getEmail());
            statement.setString(6, entity.getRole().toString());
            statement.setLong(7, entity.getId());

            statement.executeQuery();
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("deleteUser"))){
            statement.setLong(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }

    @Override
    public List<Long> checkAccess(long taskId){
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("checkTaskAccess"))){
            statement.setLong(1, taskId);
            ResultSet rs = statement.executeQuery();
            Set<Long> authorizedUserIds = new HashSet<>();
            if (rs.next()){
                authorizedUserIds.add(rs.getLong("creator_id"));
                authorizedUserIds.add(rs.getLong("owner_id"));
                authorizedUserIds.add(rs.getLong("user_id"));
            }
            while(rs.next()){
                authorizedUserIds.add(rs.getLong("user_id"));
            }
            logger.debug("users with access: {}", authorizedUserIds);
            return new ArrayList<>(authorizedUserIds);
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }

    @Override
    public List<User> findByLastName(String lastName, boolean partialMatch) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty(
                    (partialMatch) ? "findUsersByLastNamePartialMatch" : "findUsersByLastName"))){

            statement.setString(1,"%" + lastName + "%");
            statement.executeQuery();

            ResultSet rs = statement.getResultSet();
            while(rs.next()){
                users.add(new UserMapper().mapping(rs));
            }
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
        return users;
    }

    @Override
    public List<User> findByDepartment(User.Department department) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("findUsersByDepartment"))){
            statement.setString(1,department.toString());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                users.add(new UserMapper().mapping(rs));
            }
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
        return users;    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw  new UncheckedSQLException(e);
        }
    }
}
