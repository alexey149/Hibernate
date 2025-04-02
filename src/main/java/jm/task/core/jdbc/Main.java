package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {

        userService.createUsersTable();
        userService.saveUser("Shack", "Onel", (byte) 55);
        userService.saveUser("Michael", "Jordan", (byte) 60);
        userService.saveUser("Kobe", "Bryant", (byte) 42);
        userService.saveUser("Stefan", "Carry", (byte) 36);
        userService.removeUserById(3);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}