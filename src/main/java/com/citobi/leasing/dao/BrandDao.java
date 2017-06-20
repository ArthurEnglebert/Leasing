package com.citobi.leasing.dao;

import com.citobi.leasing.domain.Brand;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface BrandDao extends CrudRepository<Brand, Long> {
}
