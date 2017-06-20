package com.citobi.leasing.dao;

import com.citobi.leasing.domain.Reservation;
import com.citobi.leasing.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
public interface ReservationDao extends CrudRepository<Reservation, Long>, JpaSpecificationExecutor {
    List<Reservation> findByStartLessThanEqualAndEndGreaterThanEqual(Date endOfStart, Date startOfEnd);

    List<Reservation> findByUserAndEndGreaterThanEqual(User user, Date today);
}