package ru.mishapp.springapp.dao;

import ru.mishapp.springapp.models.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(long id);
    List<User> getAllUsers();
    User getUser(long id);
}
