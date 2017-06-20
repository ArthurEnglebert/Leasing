package com.citobi.leasing.service;

import com.citobi.leasing.domain.User;

public interface UserService {
    /**
     * Create a new user and persist it
     * @param username the username of the new user
     * @param password the password of the new user
     * @param isAdmin boolean stating if the user has admin privileges
     * @return newly persisted user
     */
    User createUser(String username, String password, boolean isAdmin);

    /**
     * Retrieve user by username
     * @param username the username of the user to get
     * @return the user if found, null otherwise
     */
    User getUser(String username);
}
