package com.citobi.leasing.service;

import com.citobi.leasing.domain.User;

public interface UserService {
    User createUser(String username, String password, boolean isAdmin);

    User getUser(String username);
}
