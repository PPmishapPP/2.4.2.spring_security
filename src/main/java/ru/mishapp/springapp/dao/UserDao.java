package ru.mishapp.springapp.dao;

import ru.mishapp.springapp.models.Role;
import ru.mishapp.springapp.models.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);
    void deleteUser(long id);
    List<User> getAllUsers();
    User getUser(long id);
    User getUserByLogin(String login);
    Role getRole(long id);
    List<Role> getAllRoles();
}
