package ru.mishapp.springapp.dao;

import org.springframework.stereotype.Repository;
import ru.mishapp.springapp.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public Role getRole(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager
                .createQuery("select role from Role role", Role.class)
                .getResultList();
    }
}
