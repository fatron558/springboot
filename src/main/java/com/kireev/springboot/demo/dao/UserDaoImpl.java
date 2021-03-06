package com.kireev.springboot.demo.dao;

import com.kireev.springboot.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        em.persist(user);
        em.flush();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = em.createQuery("from User", User.class).getResultList();
        return users;
    }

    @Override
    public User getUser(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void editUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        em.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        em.remove(getUser(id));
    }

    @Override
    public UserDetails getUserByName(String login) {
        Query query = em.createQuery("select u from User u where u.login = :login")
                .setParameter("login", login);
        return (UserDetails) query.getSingleResult();
    }
}
