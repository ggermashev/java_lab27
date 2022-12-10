package com.mephi;

import com.mephi.model.User;
import com.mephi.service.UserService;
import com.mephi.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService service = new UserServiceImpl("jdbc:postgresql://localhost:5432/java_lab27", "postgres", "postgres");
        service.createUsersTable();
        service.saveUser("name1", "lastName1", (byte)10);
        service.saveUser("name2", "lastName2", (byte)20);
        service.saveUser("name3", "lastName3", (byte)30);
        service.saveUser("name4", "lastName4", (byte)40);
        List<User> users = service.getAllUsers();
        System.out.println(users);
        service.cleanUsersTable();
        List<User> users0 = service.getAllUsers();
        System.out.println(users0);
        service.dropUsersTable();
    }
}
