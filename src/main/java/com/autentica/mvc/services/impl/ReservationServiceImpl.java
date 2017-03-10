package com.autentica.mvc.services.impl;

import com.autentica.mvc.dao.CarDao;
import com.autentica.mvc.dao.ReservationDao;
import com.autentica.mvc.dao.UserDao;
import com.autentica.mvc.models.database.Car;
import com.autentica.mvc.models.database.Reservation;
import com.autentica.mvc.models.database.User;
import com.autentica.mvc.models.form.FormModelReservation;
import com.autentica.mvc.services.ReservationService;
import com.autentica.mvc.transformers.ReservationsTransformer;
import com.autentica.mvc.transformers.impl.ReservationsTransformerImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkl on 3/6/2017.
 */

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationDao reservationDao;
    private CarDao carDao;
    private UserDao userDao;
    private ReservationsTransformer transformer;

    @Transactional
    public void addReservation(FormModelReservation reservation) throws Exception {

        Reservation databaseReservation ;
        ReservationsTransformerImpl transformer2 = new ReservationsTransformerImpl();
        databaseReservation = transformer2.transformToDatabaseModel(reservation);

        Car car = carDao.getCarById(extractIdFromData(reservation.getCar()));
        User user = userDao.getUserById(extractIdFromData(reservation.getUser()));
        databaseReservation.setUserId(user.getId());
        databaseReservation.setCarId(car.getId());

        if (!newUserHaveReservation(user.getId(), 0L) && !newCarHaveReservation(car.getId(), 0L)) {
            long reservationId = this.reservationDao.addReservation(databaseReservation);

            car.setCurrentReservationId(reservationId);
            carDao.updateCar(car);
            user.setCurrentReservationId(reservationId);
            userDao.updateUser(user);
        }




    }

    private long extractIdFromData(String userData) {

        String[] data = userData.split("|");
        return Long.valueOf(data[0]);
    }

    @Transactional
    public void updateReservation(FormModelReservation reservation) throws Exception {

        Reservation databaseReservation ;
        ReservationsTransformerImpl transformer2 = new ReservationsTransformerImpl();
        databaseReservation = transformer2.transformToDatabaseModel(reservation);

        Long carId = extractIdFromData(reservation.getCar());
        Long userId = extractIdFromData(reservation.getUser());

        databaseReservation.setUserId(userId);
        databaseReservation.setCarId(carId);

        boolean carError = updateCar(reservation);
        if(!carError){
            boolean userError = updateUser(reservation);
            if(!userError){
                this.reservationDao.updateReservation(databaseReservation);
            }
        }
    }

    private boolean updateUser(FormModelReservation reservation) {
        //Check if user updating
        //if yes check New users Reservation
        //if yes cancel operation
        //if no then delete old users reservation and set reservation to new user
        boolean error = false;


        long newUserId = extractIdFromData(reservation.getUser());
        long dbUserId = reservationDao.getUserIdByReservationId(reservation.getId());


        if (checkIsUserUpdating(newUserId, dbUserId)) {
            if (!newUserHaveReservation(newUserId, reservation.getId())) {
                updateNewUserReservation(newUserId, reservation.getId());
                updateDatabaseUserReservation(dbUserId);
            } else {
                System.out.println("User Already Have Reserved Car. Id: " + reservation.getCar());
                error = true;
            }
        }
        return error;
    }

    private boolean updateCar(FormModelReservation reservation){
        boolean error = false;

        long newCarId = extractIdFromData(reservation.getCar());
        long dbCarId = reservationDao.getCarIdByReservationId(reservation.getId());

        if (checkIsCarUpdating(newCarId, dbCarId)) {
            if (!newCarHaveReservation(newCarId, reservation.getId())) {
                updateNewCarReservation(newCarId, reservation.getId());
                updateDatabaseCarrReservation(dbCarId);
            } else {
                System.out.println("Car Already Registered by User. Id: " + reservation.getUser());
                error = true;
            }
        }
        return error;
    }

    private void updateNewCarReservation(long newCarId, long reservationId) {
        Car car = carDao.getCarById(newCarId);
        car.setCurrentReservationId(reservationId);
        carDao.updateCar(car);
    }

    private void updateDatabaseCarrReservation(long dbCarId) {
        Car dbCar = carDao.getCarById(dbCarId);
        dbCar.setCurrentReservationId(0L);
        carDao.updateCar(dbCar);
    }

    private boolean newCarHaveReservation(long newCarId, long reservationId) {
        Car car = carDao.getCarById(newCarId);
        if (car.getCurrentReservationId() == 0 || (car.getCurrentReservationId() == reservationId && reservationId !=0)) {
            return false;
        }
        return true;
    }

    private boolean checkIsCarUpdating(long newCarId, long dbCarId) {
        if (newCarId != dbCarId) {
            return true;
        }
        return false;
    }

    private void updateNewUserReservation(long newUserId, long reservationId) {
        User user = userDao.getUserById(newUserId);
        user.setCurrentReservationId(reservationId);
        userDao.updateUser(user);
    }

    private void updateDatabaseUserReservation(long dbUserId) {
        User dbUser = userDao.getUserById(dbUserId);
        dbUser.setCurrentReservationId(0L);
        userDao.updateUser(dbUser);
    }

    private boolean newUserHaveReservation(long newUserId, long reservationId) {
        User user = userDao.getUserById(newUserId);

        if (user.getCurrentReservationId() == 0 || (user.getCurrentReservationId() == reservationId && reservationId !=0)) {
            return false;
        }
        return true;
    }


    private boolean checkIsUserUpdating(long newUserId, long dbUserId) {
        if (newUserId != dbUserId) {
            return true;
        }
        return false;
    }

    @Transactional
    public void deleteReservation(long id) {

        removeReservationFromCar(id);
        removeReservationFromUser(id);
        this.reservationDao.deleteReservation(id);
    }
    private void removeReservationFromUser(long id) {

        long userId = reservationDao.getUserIdByReservationId(id);
        User user = userDao.getUserById(userId);
        user.setCurrentReservationId(0L);
        this.userDao.updateUser(user);
    }

    private void removeReservationFromCar(long id) {
        long carId = reservationDao.getCarIdByReservationId(id);
        Car car = carDao.getCarById(carId);
        car.setCurrentReservationId(0L);
        this.carDao.updateCar(car);
    }


    @Transactional
    public FormModelReservation getReservationById(long id) {
        ReservationsTransformerImpl transformer = new ReservationsTransformerImpl();
        return transformer.transformToFormModel(this.reservationDao.getReservationById(id));
    }

    @Transactional
    public List<FormModelReservation> getAllReservations() {
        ReservationsTransformerImpl transformer = new ReservationsTransformerImpl();
        List<Reservation> databaseReservations = this.reservationDao.getAllReservations();
        List<FormModelReservation> formModelReservations = new ArrayList<FormModelReservation>();

        for (Reservation dbReservation: databaseReservations) {

            FormModelReservation formModelReservation = transformer.transformToFormModel(dbReservation);
            Car car = carDao.getCarById(dbReservation.getCarId());
            User user = userDao.getUserById(dbReservation.getUserId());
            formModelReservation.setCar(car.getId() + " | " + car.getBrand() + " | " + car.getModel());
            formModelReservation.setUser(user.getId() + " | " + user.getName() + " | " + user.getSurname());
            formModelReservations.add(formModelReservation);
        }

        return formModelReservations;
    }

    @Transactional
    public List<Car> getAllCars() {
        return this.carDao.getAllCars();
    }

    @Transactional
    public List<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }

    @Transactional
    public void removeExpiredReservations() {

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        List<Reservation> expiredReservations = reservationDao.getExpiredReservations(currentTimestamp);

        for(Reservation reservation : expiredReservations){
            removeReservationFromCar(reservation.getId());
            removeReservationFromUser(reservation.getId());
            reservationDao.deleteReservation(reservation.getId());
        }

    }

    public ReservationDao getReservationDao() {
        return reservationDao;
    }

    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public CarDao getCarDao() {
        return carDao;
    }

    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
