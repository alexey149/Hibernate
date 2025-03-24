package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Jon", "Conor", (byte) 30);
        userService.saveUser("Leam", "Nilson", (byte) 23);
        userService.saveUser("Petr", "Siddorov", (byte) 25);
        userService.saveUser("Ivan", "Ivanov", (byte) 30);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.closeConnection(Util.getConnection());
    }
}
