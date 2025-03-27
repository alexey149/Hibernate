package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static UserServiceImpl userService = new UserServiceImpl();

    public static void main(String[] args) {


        userService.createUsersTable();
        userService.saveUser("Shack", "Onel", (byte) 55);
        userService.saveUser("Michael", "Jordan", (byte) 60);
        userService.saveUser("Kobe", "Bryant", (byte) 42);
        userService.saveUser("Stefan", "Carry", (byte) 36);
        userService.removeUserById(3);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.closeConnection(Util.getConnection());
    }
}
