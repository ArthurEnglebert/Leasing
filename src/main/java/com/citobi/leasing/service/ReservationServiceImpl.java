package com.citobi.leasing.service;

import com.citobi.leasing.dao.ReservationDao;
import com.citobi.leasing.domain.Car;
import com.citobi.leasing.domain.Reservation;
import com.citobi.leasing.domain.ReservationSpecifications;
import com.citobi.leasing.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    @Override
    public Reservation addReservation(User user, Car car, Date start, Date end) {
        if(!datesAreCorrect(start, end))
            throw new IllegalStateException(String.format("Dates are not correct, your End time(%s) is before your Start time(%s)", end, start));

        //get reservations in between those times
        Iterable<Reservation> reservationsInBetween = reservationDao.findByStartLessThanEqualAndEndGreaterThanEqual(end, start);

        //if only one is from our current user, it's not possible
        //if only one is for our current car, it's not possible
        for(Reservation reservation : reservationsInBetween) {
            if (reservation.getUser().equals(user)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                throw new IllegalStateException(String.format("You already reserved a car (%s - %s : %s) from %s to %s", reservation.getCar().getModel().getBrand().getName(),
                        reservation.getCar().getModel().getName(),
                        reservation.getCar().getNumberPlate(),
                        simpleDateFormat.format(reservation.getStart()),
                        simpleDateFormat.format(reservation.getEnd())));
            }
            if (reservation.getCar().equals(car)) {
                throw new IllegalStateException(String.format("The car (%s) is already reserved by someone", car.getId()));
            }
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setCar(car);
        reservation.setStart(start);
        reservation.setEnd(end);
        return reservationDao.save(reservation);
    }

    private boolean datesAreCorrect(Date start, Date end) {
        if (end.before(start))
            return false;

        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        Calendar startDay = Calendar.getInstance();
        startDay.setTime(start);
        if (startDay.get(Calendar.YEAR) < today.get(Calendar.YEAR) || startDay.get(Calendar.DAY_OF_YEAR) < today.get(Calendar.DAY_OF_YEAR))
            return false;

        Calendar tomorrow = Calendar.getInstance();
        tomorrow.setTime(new Date());
        tomorrow.add(Calendar.DATE, 1);
        Calendar endDay = Calendar.getInstance();
        endDay.setTime(end);
        if (startDay.get(Calendar.YEAR) < today.get(Calendar.YEAR) || startDay.get(Calendar.DAY_OF_YEAR) < today.get(Calendar.DAY_OF_YEAR))
            return false;

        return true;
    }

    @Override
    public Car chooseCarForPeriod(Iterable<Car> cars, Date start, Date end) {
        Iterable<Reservation> reservationIncludingThatPeriod = reservationDao.findAll(ReservationSpecifications.inPeriod(start, end));
        List<Car> carsAlreadyReserved = new ArrayList<>();

        //get all cars already reserved
        for (Reservation reservation : reservationIncludingThatPeriod)
            if (!carsAlreadyReserved.contains(reservation.getCar()))
                carsAlreadyReserved.add(reservation.getCar());

        //choose the first car not reserved that match
        //todo upgrade to choose a car based on the time it was already reserved in the past in comparison to the others, making the park more durable
        for(Car car : cars)
            if (!carsAlreadyReserved.contains(car))
                return car;

        //no car found
        return null;
    }
}
