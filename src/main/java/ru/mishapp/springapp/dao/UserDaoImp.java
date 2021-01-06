package ru.mishapp.springapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.mishapp.springapp.models.Role;
import ru.mishapp.springapp.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);

    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery(
                "select user from User user", User.class);
        return query.getResultList();
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery(
                "select user from User user where user.username = :login",
                User.class);
        query.setParameter("login", login);
        List<User> list = query.getResultList();
        if (list.isEmpty()){
            throw new UsernameNotFoundException("User " + login + "not found");
        }
        return list.get(0);
    }

    @Override
    public Role getRole(String authority) {
        TypedQuery<Role> query = entityManager.createQuery(
                "select role from Role role where role.authority = :authority", Role.class);
        query.setParameter("authority", authority);
        List<Role> list = query.getResultList();
        if (list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public void saveRole(Role role){
        entityManager.persist(role);
    }
}
