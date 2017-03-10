package com.autentica.mvc.controllers;

import com.autentica.mvc.models.database.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.autentica.mvc.services.UserService;

/**
 * Created by mkl on 3/6/2017.
 */

@Controller
public class UserController {
    private UserService userService;

    @Autowired()
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String listUsers(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("getAllUsers", this.userService.getAllUsers());
        return "users";
    }

    @RequestMapping(value = "users/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user){
        if(user.getId() == 0){
            user.setCurrentReservationId(0L);
            this.userService.addUser(user);
        } else {

            this.userService.updateUser(user);
        }

        return "redirect:/users";
    }

    @RequestMapping(value = "deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id){
        this.userService.deleteUser(((int) id));
        return "redirect:/users";
    }


    @RequestMapping(value = "editUser/{id}")
    public String updateUser(@PathVariable("id") long id, Model model){
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("getAllUsers", this.userService.getAllUsers());

        return "users";
    }

        @RequestMapping("userdata/{id}")
        public String userData(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));

        return "userdata";
    }



}
