package ru.mishapp.springapp.service;

import ru.mishapp.springapp.models.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(long id);
    User getUser(long id);
    List<User> getAllUsers();
}
