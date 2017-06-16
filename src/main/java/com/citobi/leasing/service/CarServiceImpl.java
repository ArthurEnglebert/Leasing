package com.citobi.leasing.service;


import com.citobi.leasing.dao.CarDao;
import com.citobi.leasing.domain.Car;
import com.citobi.leasing.domain.CarSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.inject.Named;
import java.util.List;

@Named
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public Car addCar() {
        Car car = null;
        try {
            car = new Car();
            return carDao.save(car);
        }
        catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            Car car = new Car(id);
            carDao.delete(car);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public Car getById(long id) {
        Car car = null;
        try {
            car = carDao.findOne(id);
        }
        catch (Exception ex) {
            return null;
        }
        return car;
    }

    @Override
    public boolean updateCar(Car car) {
        try {
            carDao.save(car);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public Iterable<Car> getAll() {
        return carDao.findAll();
    }

    @Override
    public Iterable<Car> getAvailables() {
        return carDao.findAll(CarSpecifications.carIsAvailable());
    }
}
