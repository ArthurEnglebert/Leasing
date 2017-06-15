package com.citobi.leasing.service;


import com.citobi.leasing.dao.CarDao;
import com.citobi.leasing.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named
public class CarServiceImpl implements CarService {

    //@Autowired
    //private CarDao carDao;

    @Override
    public Car addCar() {
        Car car = null;
        try {
            car = new Car();
            //carDao.save(car);
        }
        catch (Exception ex) {
            return null;
        }
        return car;
    }

    @Override
    public boolean delete(long id) {
        try {
            Car car = new Car(id);
            //carDao.delete(car);
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
            //car = carDao.findOne(id);
        }
        catch (Exception ex) {
            return null;
        }
        return car;
    }

    @Override
    public boolean updateCar(Car car) {
        try {
            //carDao.save(car);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
}
