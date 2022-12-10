package com.mephi.dao;

import com.mephi.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;
    private boolean tableExists;

    public UserDaoJDBCImpl() {
        tableExists = false;
    }

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
        tableExists = false;
    }

    @Override
    public void createUsersTable() throws SQLException {
        try {
            connection.createStatement().executeUpdate("create table if not exists Users (\n" +
                    "    id serial primary key,\n" +
                    "    name varchar(128),\n" +
                    "    lastName varchar(128),\n" +
                    "    age int\n" +
                    ");");
            tableExists = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() throws SQLException {
        try {
            connection.createStatement().executeUpdate("drop table if exists users;");
            tableExists = false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        if (tableExists) {
            try {
                connection.createStatement().executeUpdate(String.format("insert into users (name, lastName, age) values ('%s', '%s', '%d')", name, lastName, age));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        if (tableExists) {
            try {
                connection.createStatement().executeUpdate(String.format("delete from users where id=%d", id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        if (tableExists) {
            try {
                LinkedList<User> users = new LinkedList<>();
                ResultSet set = connection.createStatement().executeQuery(String.format("select id, name, lastName, age from users"));
                while(set.next()) {
                    User user = new User(set.getLong("id"), set.getString("name"), set.getString("lastName"), set.getByte("age"));
                    users.push(user);
                }
                return users;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        if (tableExists) {
            try {
                connection.createStatement().executeUpdate("truncate table users;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
