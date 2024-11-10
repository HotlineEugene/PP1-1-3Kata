package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
        super();
    }

    public void createUsersTable() {
        try (Connection conn = Util.getConnection();
        Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), last_name VARCHAR(100), age INT)");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement();) {
            stmt.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, Integer age) {
        try (Connection conn = Util.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setInt(3, age);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = Util.getConnection();
             ResultSet res = conn.createStatement().executeQuery("SELECT * FROM users")) {
            while (res.next()) {
                User user = new User(res.getString("name"),
                        res.getString("last_name"), res.getInt("age"));
                user.setId(res.getLong("id"));
                users.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement();) {
            stmt.execute("TRUNCATE TABLE users");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
