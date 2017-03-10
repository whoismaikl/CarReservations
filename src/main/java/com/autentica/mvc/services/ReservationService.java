package com.autentica.mvc.services;

import com.autentica.mvc.models.database.Car;
import com.autentica.mvc.models.database.Reservation;
import com.autentica.mvc.models.database.User;
import com.autentica.mvc.models.form.FormModelReservation;

import java.util.List;

/**
 * Created by mkl on 3/6/2017.
 */
public interface ReservationService {

    public void addReservation(FormModelReservation reservation) throws Exception;
    public void updateReservation(FormModelReservation reservation) throws Exception;
    public void deleteReservation(long id);
    public FormModelReservation getReservationById(long id);
    public List<FormModelReservation> getAllReservations();
    public List<Car> getAllCars();
    public List<User> getAllUsers();
    public void removeExpiredReservations();


}
