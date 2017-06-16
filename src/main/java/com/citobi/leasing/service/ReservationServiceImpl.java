package com.citobi.leasing.service;

import com.citobi.leasing.dao.ReservationDao;
import com.citobi.leasing.domain.Car;
import com.citobi.leasing.domain.Reservation;
import com.citobi.leasing.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Date;

@Named
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationDao reservationDao;

    @Override
    public Reservation addReservation(User user, Car car, Date start, Date end) {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setCar(car);
        reservation.setStart(start);
        reservation.setEnd(end);
        return reservationDao.save(reservation);
    }
}
