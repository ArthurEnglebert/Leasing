package com.citobi.leasing.dao;

import com.citobi.leasing.domain.Model;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ModelDao extends CrudRepository<Model, Long> {
}
