package com.citobi.leasing.service;

import com.citobi.leasing.domain.Car;

public interface CarService {
    Car addCar();
    boolean delete(long id);
    Car getById(long id);
    boolean updateCar(Car car);

    Iterable<Car> getAll();
}
