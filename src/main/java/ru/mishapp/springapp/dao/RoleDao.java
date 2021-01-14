package ru.mishapp.springapp.dao;

import ru.mishapp.springapp.models.Role;

import java.util.List;

public interface RoleDao {
    Role getRole(long id);
    List<Role> getAllRoles();
}
