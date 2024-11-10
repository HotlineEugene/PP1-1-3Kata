package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        userService.createUsersTable();
        userService.saveUser("Вова", "Вист", 78);
        userService.saveUser("Райан", "Гослинг",  74);
        userService.saveUser("Патрик", "Бейтман",  59);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
