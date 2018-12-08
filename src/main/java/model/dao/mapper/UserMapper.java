package model.dao.mapper;

import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Yuliia Tesliuk
 */
public class UserMapper {
    private User user;

    public User mapping(ResultSet rs) {
        try {
            user = User.builder()
                    .id(rs.getInt("user_id"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .department(User.Department.valueOf(rs.getString("department")))
                    .position(rs.getString("position"))
                    .email(rs.getString("email"))
                    .role(User.Role.valueOf(rs.getString("role")))
                    .login(rs.getString("login"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
