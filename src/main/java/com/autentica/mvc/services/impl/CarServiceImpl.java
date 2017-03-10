package com.autentica.mvc.services.impl;

import com.autentica.mvc.dao.CarDao;
import com.autentica.mvc.dao.ReservationDao;
import com.autentica.mvc.dao.impl.ReservationDaoImpl;
import com.autentica.mvc.models.database.Car;
import com.autentica.mvc.services.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mkl on 3/6/2017.
 */

@Service
public class CarServiceImpl implements CarService {
    private CarDao carDao;
    private ReservationDao reservationDao;


    @Transactional
    public void addCar(Car car) {
        this.carDao.addCar(car);
    }

    @Transactional
    public void updateCar(Car car) {


        car.setCurrentReservationId(reservationDao.getReservationIdByCarId(car.getId()));
        this.carDao.updateCar(car);
    }

    @Transactional
    public void deleteCar(long id) {
        this.carDao.deleteCar(id);
    }

    @Transactional
    public Car getCarById(long id) {
        return this.carDao.getCarById(id);
    }

    @Transactional
    public List<Car> getAllCars() {
        return this.carDao.getAllCars();
    }


    public CarDao getCarDao() {
        return carDao;
    }

     public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }

    public ReservationDao getReservationDao() {
        return reservationDao;
    }

    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }
}
