package com.citobi.leasing.service;

import com.citobi.leasing.domain.Car;
import com.citobi.leasing.domain.Model;

public interface CarService {
    /**
     * Add a new car in the park
     * @param numberPlate the plate number of the new car
     * @param model the model of the new car
     * @return the newly persisted car if successful, throw Exception otherwise
     */
    Car addCar(String numberPlate, Model model);

    /**
     * Get a specific car by his id
     * @param id the id of the car to retrieve
     * @return the car
     */
    Car getById(long id);

    /**
     * Update a car and persist it
     * @param car the modified car
     * @return true if the update when successful, false otherwise
     */
    boolean update(Car car);

    /**
     * Get all cars the park contains
     */
    Iterable<Car> getAll();

    /**
     * Get all cars that aren't in LockStatus.MAINTENANCE or LockStatus.REPAIR
     * @return list of cars availables
     */
    Iterable<Car> getAvailables();

    /**
     * Get cars availables (just like getAvailables()) but with a specific Model.
     * @param model the model that the cars must match
     * @return the list of cars with that specific models that are availables
     */
    Iterable<Car> getCarsAvailableWithModel(Model model);
}
