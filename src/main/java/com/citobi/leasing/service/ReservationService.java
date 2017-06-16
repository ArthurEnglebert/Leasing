package com.citobi.leasing.service;


import com.citobi.leasing.domain.Car;
import com.citobi.leasing.domain.Reservation;
import com.citobi.leasing.domain.User;

import java.util.Date;

public interface ReservationService {
    Reservation addReservation(User user, Car car, Date start, Date end);
}
