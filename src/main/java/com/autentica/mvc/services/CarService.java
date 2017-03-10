package com.autentica.mvc.services;

import com.autentica.mvc.models.database.Car;

import java.util.List;

/**
 * Created by mkl on 3/6/2017.
 */
public interface CarService {

    public void addCar(Car car);
    public void updateCar(Car car);
    public void deleteCar(long id);
    public Car getCarById(long id);
    public List<Car> getAllCars();

}
