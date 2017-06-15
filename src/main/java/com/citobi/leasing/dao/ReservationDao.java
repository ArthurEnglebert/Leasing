package com.citobi.leasing.dao;

import com.citobi.leasing.domain.Reservation;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ReservationDao extends CrudRepository<Reservation, Long> {
}