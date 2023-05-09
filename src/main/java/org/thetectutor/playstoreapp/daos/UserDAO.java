package org.thetectutor.playstoreapp.daos;

import org.thetectutor.playstoreapp.models.User;
import org.thetectutor.playstoreapp.models.UserType;
import org.thetectutor.playstoreapp.utils.DBConnect;
import org.thetectutor.playstoreapp.utils.DuplicateEntryException;

import java.sql.*;

public class UserDAO {
    private Connection con;
    private PreparedStatement ps;

    public UserDAO() {
        con = DBConnect.getConnect();
    }

    public boolean insertUser(User user) {
        String sql = "INSERT INTO users (user_name, password, age, user_type) VALUES (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAge());
            ps.setString(4, user.getType().name());

            int affectedRows = ps.executeUpdate(); // Execute the prepared statement and get the affected rows
            return affectedRows > 0; // Return true if at least one row is affected, otherwise return false
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicateEntryException("Username has been used, try another one");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User verifyUser(User user) {
        String sql = "SELECT * FROM users WHERE user_name = ? AND password = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int age = resultSet.getInt("age");
                String userTypeStr = resultSet.getString("user_type");
                UserType userType = UserType.valueOf(userTypeStr);
                user.setAge(age);
                user.setType(userType);
                return user;
            } else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
/*
* CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    age INT,
    user_name VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    user_type ENUM('USER', 'OWNER')
);
* INSERT INTO users (user_name, password, user_type, age) VALUES ('Christina', 'superhot', 'USER', 25);
* */