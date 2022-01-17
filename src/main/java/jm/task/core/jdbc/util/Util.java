package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/usersDB";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null ;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД Установлено!");
            }
        } catch (
                SQLException | ClassNotFoundException e) {
            System.out.println("Соединение с БД Закрыто!");
        }
        return connection;

    }
    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при закрытии соединения: " + e.getMessage());
        }
    }
}