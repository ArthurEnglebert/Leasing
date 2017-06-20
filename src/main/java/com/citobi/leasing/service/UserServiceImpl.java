package com.citobi.leasing.service;

import com.citobi.leasing.dao.UserDao;
import com.citobi.leasing.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Named;

@Named
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Override
    public User createUser(String username, String password, boolean isAdmin) {
        User user = null;
        try {
            String hashedPassword = passwordEncoder.encode(password);
            user = new User(username, hashedPassword);
            if(isAdmin) user.setAdmin();

            userDao.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("User with username '" + username + "' already exists.");
        }
        catch (Exception ex) {
            throw ex;
        }

        return user;
    }

    @Override
    public User getUser(String username) {
        try {
            return userDao.findByUsername(username);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
