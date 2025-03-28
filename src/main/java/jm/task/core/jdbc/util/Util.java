package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/users";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
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

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Соединение разорвано!");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при закрытии соединения: " + e.getMessage());
        }
    }

    private static SessionFactory sessionFactory = null;

    static {
        try {
            Properties settings = new Properties();
            settings.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/users");
            settings.setProperty("hibernate.connection.username", "root");
            settings.setProperty("hibernate.connection.password", "root");
            settings.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            settings.setProperty("hibernate.hbm2ddl.auto", "create");

            sessionFactory = new org.hibernate.cfg.Configuration()
                    .addProperties(settings)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public static void close() throws HibernateException {
        getSession().close();
    }
}