package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users" + "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastname VARCHAR(20), age INT(2))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO users ( name, lastname, age) VALUES (?,?,?)")) {
            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);
            pstm.executeUpdate();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void removeUserById(long id) throws SQLException {
        Statement statement = connection.createStatement();
        long userCount = 0;
        ResultSet resultSet = statement.executeQuery("SELECT id from users");
        while (resultSet.next()) {
            userCount = resultSet.getLong(1);
        }
        statement.close();
        if (id <= userCount) {
            try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
                pstm.setLong(1, id);
                pstm.executeUpdate();
            }
        } else {
            System.out.println("User с данным id не существует");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from users");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                userList.add(user);

            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return userList;

    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}