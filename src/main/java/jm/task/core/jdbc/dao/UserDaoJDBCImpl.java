package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl  implements UserDao {
    public UserDaoJDBCImpl() {}
    Connection connection = Util.getConnection();


    public void createUsersTable() { // создать таблицу пользователей

        String sqlCommand = "CREATE TABLE USERSDB (ID INT AUTO_INCREMENT PRIMARY KEY," +
                "NAME VARCHAR(45) NOT NULL," +
                "LASTNAME VARCHAR(45) NOT NULL," +
                "AGE INT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
            System.out.println("Таблица создана!");
        } catch (SQLException e) {
            System.out.println("Соединение не удалось: " + e.getMessage());
        }
    }

    public void dropUsersTable() {  // удалить таблицу пользователей
        String sqlCommand = "DROP TABLE IF EXISTS USERSDB";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
            System.out.println("Таблица пользователей успешно удалена!");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {  //сохранить пользователя
        String sqlCommand = "INSERT INTO USERSDB (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь " + name + " " + lastName + " добавлен в базу данных!");
        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении пользователя: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {  // удалить пользователя по идентификатору
        String sqlCommand = "DELETE FROM USERSDB WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Пользователь с ID " + id + " успешно удален!");
            } else {
                System.out.println("Пользователь с ID " + id + " не найде!");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {  // получить всех пользователей
        List<User> users = new ArrayList<>();
        String sqlCommand = "SELECT * FROM USERSDB";

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
            System.out.println("Ошибка при получении пользователей: " + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {  // почистить таблицу пользователей
        String sqlCommand = "DELETE FROM USERSDB";

        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlCommand);
            System.out.println("Таблица пользователей очищена!");
        }catch (SQLException e){
            System.out.println("Ошибка при очистке таблицы: " + e.getMessage());
        }
    }
}
