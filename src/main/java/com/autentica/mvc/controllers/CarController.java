package com.autentica.mvc.controllers;

import com.autentica.mvc.models.database.Car;
import com.autentica.mvc.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mkl on 3/6/2017.
 */

@Controller
public class CarController {
    private CarService carService;

    @Autowired()
    @Qualifier(value = "carService")
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "cars", method = RequestMethod.GET)
    public String listCars(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("getAllCars", this.carService.getAllCars());

        return "cars";
    }

    @RequestMapping(value = "cars/addCar", method = RequestMethod.POST)
    public String addCar(@ModelAttribute("car") Car car) {
        if (car.getId() == 0) {
            car.setCurrentReservationId(0L);
            this.carService.addCar(car);
        } else {
            this.carService.updateCar(car);
        }

        return "redirect:/cars";
    }

    @RequestMapping(value = "deleteCar/{id}")
    public String deleteCar(@PathVariable("id") long id) {
        this.carService.deleteCar(((int) id));

        return "redirect:/cars";
    }

    @RequestMapping(value = "editCar/{id}")
    public String updateCar(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", this.carService.getCarById(id));
        model.addAttribute("getAllCars", this.carService.getAllCars());

        return "cars";
    }

    @RequestMapping("cardata/{id}")
    public String carData(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", this.carService.getCarById(id));

        return "cardata";
    }


}
