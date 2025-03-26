package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    private static final Connection connection = Util.getConnection();


    public void createUsersTable() { // создать таблицу пользователей

        String sqlCommand = "CREATE TABLE IF NOT EXISTS USERS (ID INT AUTO_INCREMENT PRIMARY KEY," +
                "NAME VARCHAR(45) NOT NULL," +
                "LASTNAME VARCHAR(45) NOT NULL," +
                "AGE INT(2) NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {  // удалить таблицу пользователей
        String sqlCommand = "DROP TABLE IF EXISTS USERS";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {  //сохранить пользователя
        String sqlCommand = "INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {  // удалить пользователя по идентификатору
        String sqlCommand = "DELETE FROM USERS WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {  // получить всех пользователей
        List<User> users = new ArrayList<>();
        String sqlCommand = "SELECT * FROM USERS";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlCommand)) {
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                String lastNAme = resultSet.getString("LASTNAME");
                byte age = resultSet.getByte("AGE");
                User user = new User(name, lastNAme, age);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {  // почистить таблицу пользователей
        String sqlCommand = "DELETE FROM USERS";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
