package com.citobi.leasing.dao;

import com.citobi.leasing.domain.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
