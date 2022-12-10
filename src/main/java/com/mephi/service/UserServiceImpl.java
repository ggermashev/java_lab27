package com.mephi.service;

import com.mephi.dao.UserDaoJDBCImpl;
import com.mephi.model.User;
import com.mephi.util.Util;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoJDBCImpl dao;

    public UserServiceImpl() {}

    public UserServiceImpl(String url, String user, String password) throws SQLException {
        Util util = new Util(url,user,password);
        dao = new UserDaoJDBCImpl(util.getConnection());
    }

    @Override
    public void createUsersTable() throws SQLException {
        dao.createUsersTable();
    }

    @Override
    public void dropUsersTable() throws SQLException {
        dao.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        dao.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        dao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        dao.cleanUsersTable();
    }
}
