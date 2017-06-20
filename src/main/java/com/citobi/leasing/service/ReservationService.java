package com.citobi.leasing.service;


import com.citobi.leasing.domain.Car;
import com.citobi.leasing.domain.Reservation;
import com.citobi.leasing.domain.User;

import java.util.Date;

public interface ReservationService {
    /**
     * Add a new reservation of one user on one specific car for a given period and persist it
     * It will check if the dates are coherent and that the user isnt already reserving a car at that period of time as well as if the car isnt already reserved
     * @param user the user that reserve
     * @param car the car to be reserved
     * @param start the start time of the reservation
     * @param end the end time of the reservation
     * @return the newly persisted reservation if successful, throw exception otherwise
     */
    Reservation addReservation(User user, Car car, Date start, Date end);

    /**
     * Choose a car in a list to give to a user so that he can reserve it
     * Current algorythm is first found (correct implementation should be taking into account the amount of kilometers the cars have already been used in the last weeks/months)
     * @param cars the list of car to choose from
     * @param start the start date of the period
     * @param end the end date of the period
     * @return the chosen car if one is eligible, null otherwise
     */
    Car chooseCarForPeriod(Iterable<Car> cars, Date start, Date end);

    /**
     * Retrieve a list of reservations to come set of a specific user
     * @param user the user
     * @return the list of reservations to come for that user
     */
    Iterable<Reservation> getReservationsToComeFor(User user);
}
