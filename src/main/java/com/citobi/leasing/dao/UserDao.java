package com.citobi.leasing.dao;

import com.citobi.leasing.domain.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    /**
     * Return the user having the passed name or null if no user is found.
     * @param username the user name.
     */
    User findByUsernameAndPassword(String username, String password);
}
