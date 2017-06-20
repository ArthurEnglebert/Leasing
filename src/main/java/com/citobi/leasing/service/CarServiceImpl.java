package com.citobi.leasing.service;


import com.citobi.leasing.dao.CarDao;
import com.citobi.leasing.domain.Car;
import com.citobi.leasing.domain.CarSpecifications;
import com.citobi.leasing.domain.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public Car addCar(String plate, Model model) {
        Car car = null;
        try {
            car = new Car();
            car.setNumberPlate(plate);
            car.setModel(model);
            return carDao.save(car);
        }
        catch(DataIntegrityViolationException ex) {
            throw new IllegalStateException("A car with the plate number '"+ plate +"' already exists");
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public boolean update(Car car) {
        try {
            carDao.save(car);
            return true;
        } catch (Exception ex) {
            return false;
        }
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
    public Iterable<Car> getAll() {
        return carDao.findAll();
    }

    @Override
    public Iterable<Car> getAvailables() {
        return carDao.findAll(CarSpecifications.carIsAvailable());
    }

    @Override
    public Iterable<Car> getCarsAvailableWithModel(Model model) {
        return carDao.findAll(CarSpecifications.carsWithModel(model));
    }
}
