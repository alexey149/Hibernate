package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Jon", "Conor", (byte) 30);
        userDao.saveUser("Leam", "Nilson", (byte) 23);
        userDao.saveUser("Petr", "Siddorov", (byte) 25);
        userDao.saveUser("Ivan", "Ivanov", (byte) 30);

        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
