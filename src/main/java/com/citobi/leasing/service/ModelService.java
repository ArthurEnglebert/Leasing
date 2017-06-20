package com.citobi.leasing.service;


import com.citobi.leasing.domain.Model;

public interface ModelService {
    /**
     *
     * @return all the models of car the park contains
     */
    Iterable<Model> getAll();

    /**
     * Get a specific model from an id
     * @param id the id of the model to retrieve
     * @return the model found
     */
    Model getModelById(Long id);
}
