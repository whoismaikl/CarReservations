package com.autentica.mvc.dao.impl;

import com.autentica.mvc.dao.CarDao;
import com.autentica.mvc.models.database.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by mkl on 3/6/2017.
 */

@Repository
public class CarDaoImpl implements CarDao {

    private static final Logger logger = LoggerFactory.getLogger(CarDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    public void addCar(Car car) {

        Session session = this.sessionFactory.getCurrentSession();
        session.persist(car);
        logger.info("Car saved. Car " + car);
    }

    public void updateCar(Car car) {

        Session session = this.sessionFactory.getCurrentSession();
        session.flush();
        session.update(car);
        logger.info("Car updated. Car " + car);
    }

    public void deleteCar(long id) {
        Session session = this.sessionFactory.getCurrentSession();

        Car car = (Car) session.load(Car.class, id);
        if (car != null) {
            session.delete(car);
        }
        logger.info("Car deleted. Car " + car);
    }

    public Car getCarById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Car car = (Car) session.get(Car.class, id);
        logger.info("Car loaded. Car " + car);
        return car;
    }

    public List<Car> getAllCars() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Car> carList = session.createQuery("from Car").list();

        for (Car car : carList) {
            logger.info("Cars List + " + car);
        }

        return carList;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
