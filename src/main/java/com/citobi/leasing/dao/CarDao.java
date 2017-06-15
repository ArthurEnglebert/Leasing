package com.citobi.leasing.dao;

import com.citobi.leasing.domain.Car;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CarDao extends CrudRepository<Car, Long> {
}
