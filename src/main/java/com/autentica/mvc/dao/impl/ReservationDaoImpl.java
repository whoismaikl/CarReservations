package com.autentica.mvc.dao.impl;

import com.autentica.mvc.dao.ReservationDao;
import com.autentica.mvc.models.database.Car;
import com.autentica.mvc.models.database.Reservation;
import com.autentica.mvc.models.database.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


/**
 * Created by mkl on 3/6/2017.
 */

@Repository
public class ReservationDaoImpl implements ReservationDao {

    private static final Logger logger = LoggerFactory.getLogger(ReservationDaoImpl.class);
    private SessionFactory sessionFactory;


    public long addReservation(Reservation reservation) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(reservation);
        logger.info("Reservation saved. Reservation " + reservation);
        session.flush();
        return reservation.getId();
    }

    public void updateReservation(Reservation reservation) {

        Session session = this.sessionFactory.getCurrentSession();
        session.clear();
        session.flush();
        session.update(reservation);
        logger.info("Reservation updated. Reservation " + reservation);
    }

    public void deleteReservation(long id) {
        Session session = this.sessionFactory.getCurrentSession();

        Reservation reservation = (Reservation) session.load(Reservation.class, id);
        if (reservation != null) {
            session.delete(reservation);
        }
        logger.info("Reservation deleted. Reservation " + reservation);
    }

    public Reservation getReservationById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Reservation reservation = (Reservation) session.get(Reservation.class, id);
        logger.info("Reservation loaded. Reservation " + reservation);

        return reservation;
    }

    public List<Reservation> getAllReservations() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Reservation> reservationList = session.createQuery("from Reservation").list();

        for (Reservation reservation : reservationList) {
            logger.info("Reservations List + " + reservation);
        }

        return reservationList;
    }

    @Override
    public User getUserByReservationId(long id) {
        long userId = 0L;
        Session session = this.sessionFactory.getCurrentSession();
        Reservation reservation = getReservationById(id);
        if(reservation == null){
            userId = 0L;
        } else {
            userId = reservation.getUserId();
        }

        User user = (User) session.get(User.class, userId);
        logger.info("User loaded. User " + user);
        if(user == null){
            user = new User();
            user.setCurrentReservationId(0L);
        }
        return user;
    }

    @Override
    public Car getCarByReservationId(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Reservation reservation = getReservationById(id);
        long carId = reservation.getCarId();
        Car car = (Car) session.get(Car.class, carId);
        logger.info("Car loaded. Car " + car);

        return car;
    }

    @Override
    public long getUserIdByReservationId(long id) {

        Reservation reservation = getReservationById(id);
        long userId = reservation.getUserId();

        return userId;
    }

    @Override
    public long getCarIdByReservationId(long id) {

        Reservation reservation = getReservationById(id);
        long carId = reservation.getCarId();
        logger.info("Car id. " + carId);

        return carId;
    }

    @Override
    public long getReservationIdByUserId(long id) {

        User user = getUserByReservationId(id);
        long userReservationId = user.getCurrentReservationId();
        logger.info("userReservationId. " + userReservationId);

        return userReservationId;
    }

    @Override
    public long getReservationIdByCarId(long id) {

        User user = getUserByReservationId(id);
        long userReservationId = user.getCurrentReservationId();
        logger.info("userReservationId. " + userReservationId);

        return userReservationId;
    }

    @Override
    public List<Reservation> getExpiredReservations(Timestamp timestamp) {

        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Reservation r where r.reservedTill <= :timestamp");
        query.setParameter("timestamp", timestamp);
        List<Reservation> expiredReservationsList = query.list();

        return expiredReservationsList;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}
