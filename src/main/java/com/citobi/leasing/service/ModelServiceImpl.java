package com.citobi.leasing.service;

import com.citobi.leasing.dao.ModelDao;
import com.citobi.leasing.domain.Model;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelDao modelDao;

    @Override
    public Iterable<Model> getAll() {
        return modelDao.findAll();
    }

    @Override
    public Model getModelById(Long id) {
        return modelDao.findOne(id);
    }
}
