package com.autentica.mvc.dao;

import com.autentica.mvc.models.database.Car;
import com.autentica.mvc.models.database.Reservation;
import com.autentica.mvc.models.database.User;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mkl on 3/6/2017.
 */
public interface ReservationDao {

    public long addReservation(Reservation reservation);
    public void updateReservation(Reservation reservation);
    public void deleteReservation(long id);
    public Reservation getReservationById(long id);
    public List<Reservation> getAllReservations();
    public User getUserByReservationId(long id);
    public Car getCarByReservationId(long id);

    public long getUserIdByReservationId(long id);
    public long getCarIdByReservationId(long id);

    public long getReservationIdByUserId(long id);
    public long getReservationIdByCarId(long id);
    public List<Reservation> getExpiredReservations(Timestamp timestamp);

}
