package com.kireev.springboot.demo.dao;

import com.kireev.springboot.demo.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    List<User> getAllUsers();

    User getUser(int id);

    void editUser(User user);

    void deleteUser(int id);

    UserDetails getUserByName(String login);
}
