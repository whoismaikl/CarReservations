package com.autentica.mvc.controllers;

import com.autentica.mvc.models.database.Car;
import com.autentica.mvc.models.database.Reservation;
import com.autentica.mvc.models.database.User;
import com.autentica.mvc.models.form.FormModelReservation;
import com.autentica.mvc.services.CarService;
import com.autentica.mvc.services.InputValidator;
import com.autentica.mvc.services.ReservationService;
import com.autentica.mvc.services.UserService;
import com.autentica.mvc.services.timeServices.Time;
import com.autentica.mvc.transformers.ReservationsTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mkl on 3/6/2017.
 */

@Controller
public class ReservationController {

    private InputValidator validator;
    private ReservationService reservationService;
    private UserService userService;
    private CarService carService;


    @Autowired()
    @Qualifier(value = "reservationService")
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
       // this.reservationService.removeExpiredReservations();

    }

    @RequestMapping(value = "reservations", method = RequestMethod.GET)
    public String listReservations(Model model) {
        model.addAttribute("reservation", new FormModelReservation()); //!!!
        model.addAttribute("getAllReservations", this.reservationService.getAllReservations());

        model.addAttribute("car", new Car());
        model.addAttribute("getAllCars", this.reservationService.getAllCars());

        model.addAttribute("user", new User());
        model.addAttribute("getAllUsers", this.reservationService.getAllUsers());


        return "reservations";
    }

    @RequestMapping(value = "reservations/addReservation", method = RequestMethod.POST)
    public String addReservation(@ModelAttribute("reservation") FormModelReservation reservation, BindingResult result) {

        //validator.validate(reservation, result);
        if (reservation.getId() == 0) {
            try {
                this.reservationService.addReservation(reservation);
            } catch (Exception e) {
                System.out.println("Dissallowed action");
            }
        } else {
          //  this.reservationService.updateReservation(transformer.transformToDatabaseModel(reservation));
            try {
                this.reservationService.updateReservation(reservation);
            } catch (Exception e) {
                System.out.println("Dissallowed action");
            }
        }

        return "redirect:/reservations";
    }

    @RequestMapping(value = "editReservation/{id}")
    public String updateReservation(@PathVariable("id") long id, Model model) {
        model.addAttribute("reservation", this.reservationService.getReservationById(id));
        model.addAttribute("getAllReservations", this.reservationService.getAllReservations());

        model.addAttribute("getAllCars", this.reservationService.getAllCars());
        model.addAttribute("getAllUsers", this.reservationService.getAllUsers());

        return "reservations";
    }

    @RequestMapping(value = "deleteReservation/{id}")
    public String deleteReservation(@PathVariable("id") long id) {
        this.reservationService.deleteReservation(((int) id));

        return "redirect:/reservations";
    }

    @RequestMapping(value = "reservationsUser/{id}")
    public String userData(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));

        return "userdata";
    }

    @RequestMapping(value = "reservationsCar/{id}")
    public String carData(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", this.carService.getCarById(id));

        return "cardata";
    }

}
